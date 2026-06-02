import java.util.ArrayList;
import java.util.List;

/**
 * Cart 클래스
 * - 장바구니에 담긴 주문 항목 목록과 총 금액을 관리합니다.
 * - Controller는 Cart 객체를 통해 주문 항목 추가, 삭제, 결제 가능 여부 확인을 수행합니다.
 */
public class Cart {
    private List<OrderItem> cartItem;
    private int totalPrice;

    public Cart() {
        this.cartItem = new ArrayList<>();
        this.totalPrice = 0;
    }

    public void addItem(OrderItem item) {
        cartItem.add(item);
        totalPrice += item.getTotalPrice();
    }

    public void removeItem(int index) {
        if (index < 0 || index >= cartItem.size()) {
            throw new IllegalArgumentException("삭제할 주문 항목이 올바르지 않습니다.");
        }

        OrderItem removed = cartItem.remove(index);
        totalPrice -= removed.getTotalPrice();
    }

    public void clear() {
        cartItem.clear();
        totalPrice = 0;
    }

    public boolean isEmpty() {
        return cartItem.isEmpty();
    }

    public List<OrderItem> getCartItem() {
        return cartItem;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
