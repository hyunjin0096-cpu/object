import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * KioskController 클래스
 *
 * [역할]
 * - View의 버튼을 가져와 addActionListener를 연결합니다.
 * - 사용자의 메뉴 선택, 옵션 선택, 장바구니 추가, 결제 흐름을 처리합니다.
 * - View가 직접 Model을 수정하지 않고 Controller를 통해 Model에 접근하도록 만듭니다.
 *
 * [중요]
 * - 아래 코드는 View 친구가 getter 메서드를 만들어준다는 전제를 둡니다.
 * - 예: mainPanel.getNextBtn(), menuPanel.getVanillaBtn() 등
 * - 실제 View 파일의 getter 이름이 다르면, 이 Controller 안의 getter 호출 이름만 맞춰 바꾸면 됩니다.
 */
public class KioskController {

    // ===== View 객체 =====
    private MainFrame mainFrame;
    private MainPanel mainPanel;
    private MenuPanel menuPanel;
    private OptionPanel optionPanel;
    private CartPanel cartPanel;
    private OrderCompletePanel orderCompletePanel;

    // ===== Model 객체 =====
    private Menu selectedMenu;                     // 사용자가 선택한 메뉴
    private ServingOption selectedServingOption;   // 컵/콘 선택
    private List<ToppingOption> selectedToppings;   // 선택된 토핑 목록
    private Cart cart;                             // 장바구니
    private OrderFileManager orderFileManager;      // 주문 저장 담당

    // 주문 번호는 간단하게 1번부터 증가하도록 처리합니다.
    private int nextOrderNumber = 1;

    /**
     * Controller 생성자
     * - 각 View 객체를 전달받고, Model 객체를 초기화한 뒤, 버튼 이벤트를 연결합니다.
     */
    public KioskController(MainFrame mainFrame,
                           MainPanel mainPanel,
                           MenuPanel menuPanel,
                           OptionPanel optionPanel,
                           CartPanel cartPanel,
                           OrderCompletePanel orderCompletePanel) {
        this.mainFrame = mainFrame;
        this.mainPanel = mainPanel;
        this.menuPanel = menuPanel;
        this.optionPanel = optionPanel;
        this.cartPanel = cartPanel;
        this.orderCompletePanel = orderCompletePanel;

        this.selectedToppings = new ArrayList<>();
        this.cart = new Cart();
        this.orderFileManager = new OrderFileManager("order_history.txt");

        connectEvents();
    }

    /**
     * 전체 버튼 이벤트를 한 곳에서 연결합니다.
     * - View는 버튼을 제공하고, Controller는 버튼의 동작을 정합니다.
     */
    private void connectEvents() {
        connectMainPanelEvents();
        connectMenuPanelEvents();
        connectOptionPanelEvents();
        connectCartPanelEvents();
        connectOrderCompletePanelEvents();
    }

    /**
     * 시작 화면 이벤트 연결
     */
    private void connectMainPanelEvents() {
        // 시작 버튼을 누르면 메뉴 선택 화면으로 이동합니다.
        mainPanel.getNextBtn().addActionListener(e -> {
            mainFrame.showPanel("menu");
        });
    }

    /**
     * 메뉴 선택 화면 이벤트 연결
     */
    private void connectMenuPanelEvents() {
        // 바닐라 선택
        menuPanel.getVanillaBtn().addActionListener(e -> {
            selectedMenu = new Menu("vanilla", 3500);
            showMessage("바닐라가 선택되었습니다.");
        });

        // 딸기 선택
        menuPanel.getStrawberryBtn().addActionListener(e -> {
            selectedMenu = new Menu("strawberry", 3500);
            showMessage("딸기가 선택되었습니다.");
        });

        // 초코 선택
        menuPanel.getChocoBtn().addActionListener(e -> {
            selectedMenu = new Menu("choco", 3500);
            showMessage("초코가 선택되었습니다.");
        });

        // 다음 버튼: 메뉴가 선택되었을 때만 옵션 화면으로 이동합니다.
        menuPanel.getNextBtn().addActionListener(e -> {
            if (selectedMenu == null) {
                showWarning("메뉴를 먼저 선택하세요.");
                return;
            }
            mainFrame.showPanel("option");
        });

        // 이전 버튼: 시작 화면으로 돌아갑니다.
        menuPanel.getBackBtn().addActionListener(e -> {
            mainFrame.showPanel("main");
        });
    }

    /**
     * 옵션 선택 화면 이벤트 연결
     */
    private void connectOptionPanelEvents() {
        // 컵 선택
        optionPanel.getCupBtn().addActionListener(e -> {
            selectedServingOption = ServingOption.CUP;
        });

        // 콘 선택
        optionPanel.getConeBtn().addActionListener(e -> {
            selectedServingOption = ServingOption.CONE;
        });

        // 다음 버튼: 선택된 메뉴와 옵션을 OrderItem으로 만들고 장바구니에 추가합니다.
        optionPanel.getNextBtn().addActionListener(e -> {
            if (selectedServingOption == null) {
                showWarning("컵 또는 콘을 선택하세요.");
                return;
            }

            // 토핑 선택값은 버튼 클릭 순간마다 저장하기보다,
            // 다음 버튼을 눌렀을 때 한 번에 읽어오는 방식이 단순합니다.
            selectedToppings = readSelectedToppingsFromView();

            OrderItem item = new OrderItem(selectedMenu, selectedServingOption, selectedToppings);
            cart.addItem(item);

            updateCartView();
            resetCurrentSelection();
            mainFrame.showPanel("cart");
        });

        // 이전 버튼: 메뉴 선택 화면으로 돌아갑니다.
        optionPanel.getBackBtn().addActionListener(e -> {
            mainFrame.showPanel("menu");
        });
    }

    /**
     * 장바구니 화면 이벤트 연결
     */
    private void connectCartPanelEvents() {
        // 결제 버튼: 장바구니가 비어 있지 않을 때만 결제 처리합니다.
        cartPanel.getPaymentBtn().addActionListener(e -> {
            if (cart.isEmpty()) {
                showWarning("장바구니가 비어 있습니다.");
                return;
            }
            completeOrder();
        });

        // 항목 삭제 버튼: View에서 선택된 인덱스를 가져와 삭제합니다.
        cartPanel.getDeleteBtn().addActionListener(e -> {
            int selectedIndex = cartPanel.getSelectedItemIndex();

            if (selectedIndex < 0) {
                showWarning("삭제할 항목을 선택하세요.");
                return;
            }

            try {
                cart.removeItem(selectedIndex);
                updateCartView();
            } catch (IllegalArgumentException ex) {
                showWarning(ex.getMessage());
            }
        });

        // 전체 삭제 버튼
        cartPanel.getClearBtn().addActionListener(e -> {
            cart.clear();
            updateCartView();
        });

        // 더 담기 버튼: 메뉴 선택 화면으로 이동합니다.
        cartPanel.getAddMoreBtn().addActionListener(e -> {
            mainFrame.showPanel("menu");
        });
    }

    /**
     * 주문 완료 화면 이벤트 연결
     */
    private void connectOrderCompletePanelEvents() {
        // 처음 화면으로 돌아가는 버튼입니다.
        orderCompletePanel.getHomeBtn().addActionListener(e -> {
            cart.clear();
            updateCartView();
            mainFrame.showPanel("main");
        });
    }

    /**
     * OptionPanel에서 체크된 토핑을 읽어옵니다.
     * - View 친구가 체크박스 getter를 만들어줘야 합니다.
     */
    private List<ToppingOption> readSelectedToppingsFromView() {
        List<ToppingOption> toppings = new ArrayList<>();

        if (optionPanel.getChocoChipCheck().isSelected()) {
            toppings.add(ToppingOption.CHOCOCHIP);
        }

        if (optionPanel.getHoneyCheck().isSelected()) {
            toppings.add(ToppingOption.HONEY);
        }

        if (optionPanel.getCerealCheck().isSelected()) {
            toppings.add(ToppingOption.CEREAL);
        }

        return toppings;
    }

    /**
     * 장바구니 화면을 최신 상태로 갱신합니다.
     * - CartPanel 쪽에 주문 목록과 총 금액을 화면에 반영하는 메서드가 필요합니다.
     */
    private void updateCartView() {
        cartPanel.updateCartList(cart.getCartItem());
        cartPanel.updateTotalPrice(cart.getTotalPrice());
    }

    /**
     * 주문 완료 처리
     * - 주문 번호 생성
     * - 주문 내역 파일 저장
     * - 완료 화면에 주문 번호와 금액 표시
     * - 완료 화면으로 이동
     */
    private void completeOrder() {
        Order order = new Order(nextOrderNumber, cart.getCartItem(), cart.getTotalPrice());
        nextOrderNumber++;

        try {
            orderFileManager.saveOrder(order);
        } catch (IOException ex) {
            showWarning("주문 내역 저장 중 오류가 발생했습니다.");
            return;
        }

        orderCompletePanel.setOrderNumber(order.getOrderNumber());
        orderCompletePanel.setTotalPrice(order.getTotalPrice());

        mainFrame.showPanel("complete");
    }

    /**
     * 하나의 주문 항목을 장바구니에 넣은 뒤,
     * 다음 주문을 위해 현재 선택값을 초기화합니다.
     */
    private void resetCurrentSelection() {
        selectedMenu = null;
        selectedServingOption = null;
        selectedToppings.clear();
    }

    /**
     * 간단한 안내 메시지 출력
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(mainFrame, message);
    }

    /**
     * 경고 메시지 출력
     */
    private void showWarning(String message) {
        JOptionPane.showMessageDialog(mainFrame, message, "안내", JOptionPane.WARNING_MESSAGE);
    }
}
