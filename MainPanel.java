import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JButton nextBtn;

    public MainPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(250, 248, 245));

        JLabel logoLabel = new JLabel(" 지능형 아이스크림 키오스크", SwingConstants.CENTER);
        logoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        add(logoLabel, BorderLayout.CENTER);

        nextBtn = new JButton("화면을 터치하면 주문이 시작됩니다.");
        nextBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        nextBtn.setBackground(new Color(43, 108, 176));
        nextBtn.setForeground(Color.PINK);
        nextBtn.setPreferredSize(new Dimension(450, 80));
        add(nextBtn, BorderLayout.SOUTH);
    }

    public JButton getNextBtn() { return nextBtn; }
}