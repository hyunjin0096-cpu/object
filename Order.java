import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Order 클래스
 * - 결제가 완료된 최종 주문 정보를 저장합니다.
 */
public class Order {
    private int orderNumber;
    private LocalDateTime orderTime;
    private List<OrderItem> orderItems;
    private int totalPrice;

    public Order(int orderNumber, List<OrderItem> orderItems, int totalPrice) {
        this.orderNumber = orderNumber;
        this.orderTime = LocalDateTime.now();
        this.orderItems = new ArrayList<>(orderItems);
        this.totalPrice = totalPrice;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
