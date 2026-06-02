import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel {
    private JRadioButton cupRadio, coneRadio;
    private ButtonGroup servingGroup;
    private JCheckBox chocochipCheck, honeyCheck, cerealCheck;
    private JButton nextBtn;

    public OptionPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("옵션 및 토핑 추가", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // 1. Serving Option (cup/cone) - 콘은 500원 추가 명세 적용
        JPanel servingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        servingPanel.setBorder(BorderFactory.createTitledBorder("용기 선택 (Serving Option)"));
        cupRadio = new JRadioButton("컵 (cup) +0원", true);
        coneRadio = new JRadioButton("콘 (cone) +500원");
        servingGroup = new ButtonGroup();
        servingGroup.add(cupRadio);
        servingGroup.add(coneRadio);
        servingPanel.add(cupRadio);
        servingPanel.add(coneRadio);

        // 2. Topping Option 명세 일치화
        JPanel toppingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toppingPanel.setBorder(BorderFactory.createTitledBorder("토핑 추가 (Topping Option)"));
        chocochipCheck = new JCheckBox("초코칩 (chocochip) +1,000원");
        honeyCheck = new JCheckBox("꿀 (honey) +500원");
        cerealCheck = new JCheckBox("시리얼 (cereal) +1,000원");
        toppingPanel.add(chocochipCheck);
        toppingPanel.add(honeyCheck);
        toppingPanel.add(cerealCheck);

        centerPanel.add(servingPanel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(toppingPanel);
        add(centerPanel, BorderLayout.CENTER);

        nextBtn = new JButton("장바구니 담기 및 다음");
        nextBtn.setBackground(new Color(43, 108, 176));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        add(nextBtn, BorderLayout.SOUTH);
    }

    public JRadioButton getCupRadio() { return cupRadio; }
    public JRadioButton getConeRadio() { return coneRadio; }
    public JCheckBox getChocochipCheck() { return chocochipCheck; }
    public JCheckBox getHoneyCheck() { return honeyCheck; }
    public JCheckBox getCerealCheck() { return cerealCheck; }
    
    // 현진님 요청 반영: 다음 단계 진입 버튼 명칭 단일화
    public JButton getNextBtn() { return nextBtn; }
}
