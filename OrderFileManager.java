import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * OrderFileManager 클래스
 * - 주문 내역을 파일에 저장하는 역할을 담당합니다.
 * - 파일 I/O 요구사항을 충족하기 위한 보조 클래스입니다.
 */
public class OrderFileManager {
    private String fileName;

    public OrderFileManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * append 모드로 주문 내역을 저장합니다.
     * 프로그램을 여러 번 실행해도 이전 주문 기록이 사라지지 않습니다.
     */
    public void saveOrder(Order order) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("주문번호: " + order.getOrderNumber());
            writer.newLine();
            writer.write("주문시간: " + order.getOrderTime());
            writer.newLine();
            writer.write("총금액: " + order.getTotalPrice() + "원");
            writer.newLine();
            writer.write("주문항목:");
            writer.newLine();

            for (OrderItem item : order.getOrderItems()) {
                writer.write("- " + item.toDisplayText());
                writer.newLine();
            }

            writer.write("-----------------------------");
            writer.newLine();
        }
    }
}
