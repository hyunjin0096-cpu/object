import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CartPanel extends JPanel {
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private JLabel totalPriceLabel;
    private JButton nextBtn; // [주문 완료하기] 버튼

    public CartPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. 상단 타이틀 라벨
        JLabel titleLabel = new JLabel("🛒 장바구니 주문 목록", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // 2. 중앙 주문 내역 표 (JTable) 영역
        // ⚠️ 에러가 났던 24번째 줄 부근의 배열 문법을 자바 표준 규격으로 올바르게 수정했습니다.
        String[] columns = {"메뉴명", "용기(Serving)", "토핑 리스트", "총 금액"};
        
        // 테이블 모델 생성 및 컬럼명 설정
        tableModel = new DefaultTableModel(columns, 0);
        orderTable = new JTable(tableModel);
        
        // 표에 데이터가 많아질 때를 대비해 스크롤 패널로 감싸기
        JScrollPane scrollPane = new JScrollPane(orderTable);
        add(scrollPane, BorderLayout.CENTER);

        // 3. 하단 금액 표시 및 버튼 영역
        JPanel southPanel = new JPanel(new BorderLayout(5, 5));
        totalPriceLabel = new JLabel("최종 결제 금액: 0원", SwingConstants.RIGHT);
        totalPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        totalPriceLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 5));

        // 결제를 생략하고 바로 주문 완료 화면으로 넘어가는 버튼
        nextBtn = new JButton("주문 완료하기 (결제 생략)");
        nextBtn.setBackground(new Color(229, 62, 62)); // 강조용 레드 색상
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        southPanel.add(totalPriceLabel, BorderLayout.NORTH);
        southPanel.add(nextBtn, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);
    }

    // 연우(Controller)가 데이터(시은이의 Model 값들)를 가져와서 표와 라벨을 업데이트할 때 쓸 Getter 메서드들
    public DefaultTableModel getTableModel() { return tableModel; }
    public JLabel getTotalPriceLabel() { return totalPriceLabel; }
    
    // 연우(Controller)가 가져가서 addActionListener를 달아줄 다음 단계 버튼
    public JButton getNextBtn() { return nextBtn; }
}