import javax.swing.*;
import java.awt.*;

public class OrderCompletePanel extends JPanel {
    private JLabel orderNumberLabel;
    private JButton nextBtn; // 다시 처음으로 돌아가는 버튼

    public OrderCompletePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(247, 250, 252));

        JLabel topLabel = new JLabel("주문이 정상적으로 완료되었습니다!", SwingConstants.CENTER);
        topLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        topLabel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
        add(topLabel, BorderLayout.NORTH);

        orderNumberLabel = new JLabel("0028", SwingConstants.CENTER); // 28조 기념 기본번호
        orderNumberLabel.setFont(new Font("Arial", Font.BOLD, 64));
        orderNumberLabel.setForeground(new Color(43, 108, 176));
        add(orderNumberLabel, BorderLayout.CENTER);

        nextBtn = new JButton("처음 화면으로 돌아가기");
        nextBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        nextBtn.setBackground(new Color(43, 108, 176));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setPreferredSize(new Dimension(450, 60));
        add(nextBtn, BorderLayout.SOUTH);
    }

    public JLabel getOrderNumberLabel() { return orderNumberLabel; }
    public JButton getNextBtn() { return nextBtn; }
    
    public void setOrderNumber(String orderNum) {
        orderNumberLabel.setText(orderNum);
    }
}