public class KioskLauncher {
    public static void main(String[] args) {
        // 1. 메인 프레임 생성 [cite: 74]
        MainFrame frame = new MainFrame();

        // 2. 수정된 화면 부품(Panel)들 생성 (PaymentPanel 제거!)
        MainPanel mainPanel = new MainPanel();
        MenuPanel menuPanel = new MenuPanel();
        OptionPanel optionPanel = new OptionPanel();
        CartPanel cartPanel = new CartPanel();
        OrderCompletePanel completePanel = new OrderCompletePanel();

        // 3. 메인 프레임에 남은 부품들 탑승시키기
        frame.getMainPanel().add(mainPanel, "MainPanel");
        frame.getMainPanel().add(menuPanel, "MenuPanel");
        frame.getMainPanel().add(optionPanel, "OptionPanel");
        frame.getMainPanel().add(cartPanel, "CartPanel");
        frame.getMainPanel().add(completePanel, "CompletePanel");

        // 4. 이벤트 연결 (장바구니에서 -> 바로 주문 완료 화면으로 다이렉트 전환!)
        mainPanel.getNextBtn().addActionListener(e -> frame.showPanel("MenuPanel"));
        menuPanel.getNextBtn().addActionListener(e -> frame.showPanel("OptionPanel"));
        optionPanel.getNextBtn().addActionListener(e -> frame.showPanel("CartPanel"));
        
        // ⭐ 이 부분이 수정되었습니다: 장바구니 버튼 클릭 시 바로 완료 화면 이동
        cartPanel.getNextBtn().addActionListener(e -> frame.showPanel("CompletePanel"));
        
        // 완료 화면에서 버튼 누르면 다시 처음으로 순환 [cite: 91]
        completePanel.getNextBtn().addActionListener(e -> frame.showPanel("MainPanel"));

        // 5. 첫 화면 보여주고 켜기
        frame.showPanel("MainPanel");
        frame.setVisible(true);
    }
}