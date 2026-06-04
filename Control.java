// Control.java
// View와 Model을 연결하는 Controller 파일

import javax.swing.JOptionPane;

public class Control {

    // View 객체들
    private MainFrame mainFrame;
    private MainPanel mainPanel;
    private MenuPanel menuPanel;
    private OptionPanel optionPanel;
    private CartPanel cartPanel;
    private OrderCompletePanel orderCompletePanel;

    // Model 객체들
    private Cart cart;
    private Menu selectedMenu;

    // 생성자: Main에서 만든 View 객체와 Model 객체를 받아와 연결함
    public Control(
            MainFrame mainFrame,
            MainPanel mainPanel,
            MenuPanel menuPanel,
            OptionPanel optionPanel,
            CartPanel cartPanel,
            OrderCompletePanel orderCompletePanel,
            Cart cart
    ) {
        this.mainFrame = mainFrame;
        this.mainPanel = mainPanel;
        this.menuPanel = menuPanel;
        this.optionPanel = optionPanel;
        this.cartPanel = cartPanel;
        this.orderCompletePanel = orderCompletePanel;
        this.cart = cart;

        connectEvents();
    }

    // View의 버튼에 이벤트를 연결하는 메서드
    private void connectEvents() {

        // 시작 화면 → 메뉴 선택 화면
        mainPanel.getNextBtn().addActionListener(e -> {
            mainFrame.changePanel(menuPanel);
        });

        // 초코 선택
        menuPanel.getChocoRadio().addActionListener(e -> {
            selectedMenu = new Menu("choco", 3500);
        });

        // 바닐라 선택
        menuPanel.getVanillaRadio().addActionListener(e -> {
            selectedMenu = new Menu("vanilla", 3500);
        });

        // 딸기 선택
        menuPanel.getStrawberryRadio().addActionListener(e -> {
            selectedMenu = new Menu("strawberry", 3500);
        });

        // 메뉴 선택 화면 → 옵션 화면
        menuPanel.getNextBtn().addActionListener(e -> {
            if (selectedMenu == null) {
                selectedMenu = new Menu(menuPanel.getSelectedFlavor(), 3500);
            }

            mainFrame.changePanel(optionPanel);
        });

        // 옵션 화면 → 장바구니 화면
        optionPanel.getNextBtn().addActionListener(e -> {
            addCurrentOrderToCart();
            refreshCartPanel();
            mainFrame.changePanel(cartPanel);
        });

        // 장바구니 화면 → 주문 완료 화면
        cartPanel.getNextBtn().addActionListener(e -> {
            orderCompletePanel.setOrderNumber("0028");
            mainFrame.changePanel(orderCompletePanel);
        });

        // 주문 완료 화면 → 처음 화면
        orderCompletePanel.getNextBtn().addActionListener(e -> {
            cart.clear();
            selectedMenu = null;
            refreshCartPanel();
            mainFrame.changePanel(mainPanel);
        });
    }

    // 현재 선택된 메뉴와 옵션을 OrderItem으로 만들어 Cart에 추가
    private void addCurrentOrderToCart() {
        if (selectedMenu == null) {
            JOptionPane.showMessageDialog(null, "메뉴를 먼저 선택해주세요.");
            return;
        }

        ServingOption servingOption;

        if (optionPanel.getConeRadio().isSelected()) {
            servingOption = new ServingOption(ServingOption.Type.CONE);
        } else {
            servingOption = new ServingOption(ServingOption.Type.CUP);
        }

        OrderItem orderItem = new OrderItem(selectedMenu, servingOption);

        if (optionPanel.getChocochipCheck().isSelected()) {
            orderItem.addTopping(new ToppingOption(ToppingOption.Type.CHOCOCHIP));
        }

        if (optionPanel.getHoneyCheck().isSelected()) {
            orderItem.addTopping(new ToppingOption(ToppingOption.Type.HONEY));
        }

        if (optionPanel.getCerealCheck().isSelected()) {
            orderItem.addTopping(new ToppingOption(ToppingOption.Type.CEREAL));
        }

        cart.addItem(orderItem);
    }

    // Cart에 담긴 주문 항목을 CartPanel 화면에 다시 표시
    private void refreshCartPanel() {
        cartPanel.getTableModel().setRowCount(0);

        for (OrderItem item : cart.getCartItems()) {
            cartPanel.addOrderToTable(
                    item.getMenuName(),
                    item.getServingOption().getOptionName(),
                    item.toppingsSummary(),
                    item.getTotalPrice()
            );
        }

        cartPanel.setTotalPrice(cart.getTotalPrice());
    }
}
