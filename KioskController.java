import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class KioskController {
    private MainFrame mainFrame;
    private MainPanel mainPanel;
    private MenuPanel menuPanel;
    private OptionPanel optionPanel;
    private CartPanel cartPanel;
    private OrderCompletePanel completePanel;

    // 생성자에서 현진이가 만든 모든 View 컴포넌트들을 전달받음
    public KioskController(MainFrame mainFrame, MainPanel mainPanel, MenuPanel menuPanel, 
                           OptionPanel optionPanel, CartPanel cartPanel, OrderCompletePanel completePanel) {
        this.mainFrame = mainFrame;
        this.mainPanel = mainPanel;
        this.menuPanel = menuPanel;
        this.optionPanel = optionPanel;
        this.cartPanel = cartPanel;
        this.completePanel = completePanel;

        // 이벤트 리스너 연결 시작
        initEvent();
    }

    private void initEvent() {
        // 1. 시작 화면: [getNextBtn()] 누르면 맛 선택 화면 이동
        mainPanel.getNextBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("MenuPanel");
            }
        });

        // 2. 맛 선택 화면: [getNextBtn()] 누르면 옵션 화면 이동
        menuPanel.getNextBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMenu = "";
                if (menuPanel.getChocoRadio().isSelected()) {
                    selectedMenu = "choco";
                } else if (menuPanel.getVanillaRadio().isSelected()) {
                    selectedMenu = "vanilla";
                } else if (menuPanel.getStrawberryRadio().isSelected()) {
                    selectedMenu = "strawberry";
                }
                
                // [콘솔 확인용] 나중에 시은이(Model)의 Menu 클래스나 OrderItem 데이터와 연동할 부분
                System.out.println("선택된 메뉴: " + selectedMenu);
                
                mainFrame.showPanel("OptionPanel");
            }
        });

        // 3. 옵션 선택 화면: [getNextBtn()] 누르면 바로 장바구니 화면 이동
        optionPanel.getNextBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 용기 선택 값 읽기 (cup / cone)
                String serving = optionPanel.getCupRadio().isSelected() ? "cup" : "cone";
                
                // 토핑 선택 리스트 수집 (chocochip, honey, cereal)
                List<String> toppings = new ArrayList<>();
                if (optionPanel.getChocochipCheck().isSelected()) toppings.add("chocochip");
                if (optionPanel.getHoneyCheck().isSelected()) toppings.add("honey");
                if (optionPanel.getCerealCheck().isSelected()) toppings.add("cereal");

                // [콘솔 확인용] 수집된 데이터는 시은이(Model)의 Cart 객체로 넘어갈 예정
                System.out.println("선택된 용기: " + serving);
                System.out.println("선택된 토핑들: " + toppings);

                mainFrame.showPanel("CartPanel");
            }
        });

        // 4. 장바구니 화면: 수정 요구사항 반영! 결제 단계 생략하고 [getNextBtn()] 누르면 주문 완료로 다이렉트 이동
        cartPanel.getNextBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 실시간 주문 번호 세팅 시뮬레이션 (28조 기념 0028번)
                completePanel.setOrderNumber("0028");
                
                mainFrame.showPanel("CompletePanel");
            }
        });

        // 5. 주문 완료 화면: [getNextBtn()] 누르면 다시 첫 시작 화면으로 복귀 (순환 구조)
        completePanel.getNextBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("MainPanel");
            }
        });
    }
}