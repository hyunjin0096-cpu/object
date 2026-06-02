import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JRadioButton chocoRadio;
    private JRadioButton vanillaRadio;
    private JRadioButton strawberryRadio;
    private ButtonGroup menuGroup;
    private JButton nextBtn; // 연우가 가져갈 버튼 이름 통일

    public MenuPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("아이스크림 플레이버를 선택하세요", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        
        // 회의에서 정한 영문 명세와 단가 반영
        chocoRadio = new JRadioButton("초코 (choco) - 3,500원", true);
        vanillaRadio = new JRadioButton("바닐라 (vanilla) - 3,500원");
        strawberryRadio = new JRadioButton("딸기 (strawberry) - 3,500원");

        Font font = new Font("맑은 고딕", Font.PLAIN, 14);
        chocoRadio.setFont(font);
        vanillaRadio.setFont(font);
        strawberryRadio.setFont(font);

        menuGroup = new ButtonGroup();
        menuGroup.add(chocoRadio);
        menuGroup.add(vanillaRadio);
        menuGroup.add(strawberryRadio);

        centerPanel.add(chocoRadio);
        centerPanel.add(vanillaRadio);
        centerPanel.add(strawberryRadio);
        add(centerPanel, BorderLayout.CENTER);

        nextBtn = new JButton("맛 선택 완료 및 다음");
        nextBtn.setBackground(new Color(43, 108, 176));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        add(nextBtn, BorderLayout.SOUTH);
    }

    // 연우(Controller)가 낚아채서 데이터를 판단할 게터들
    public JRadioButton getChocoRadio() { return chocoRadio; }
    public JRadioButton getVanillaRadio() { return vanillaRadio; }
    public JRadioButton getStrawberryRadio() { return strawberryRadio; }
    
    // 현진님 요청 반영: 다음 단계 진입 버튼 명칭 단일화
    public JButton getNextBtn() { return nextBtn; }
}