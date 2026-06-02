// src/model/Cart.java
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<OrderItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        if (item == null) throw new IllegalArgumentException("item은 null일 수 없습니다.");
        cartItems.add(item);
    }

    public void removeItem(int index) {
        if (index < 0 || index >= cartItems.size()) {
            throw new IndexOutOfBoundsException("유효하지 않은 index: " + index);
        }
        cartItems.remove(index);
    }

    public void clear() {
        cartItems.clear();
    }

    public List<OrderItem> getCartItems() {
        return Collections.unmodifiableList(cartItems);
    }

    public int getTotalPrice() {
        int sum = 0;
        for (OrderItem item : cartItems) {
            sum += item.getTotalPrice();
        }
        return sum;
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public int size() {
        return cartItems.size();
    }
}
