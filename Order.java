package model;

import java.util.List;

public class Order {
    private final Cart cart;
    private final MenuCatalog menuCatalog;
    private OrderItem currentItem;

    public Order() {
        this.cart = new Cart();
        this.menuCatalog = MenuCatalog.defaultCatalog();
        this.currentItem = null;
    }

    public void startNewItem(String flavor) {
        Menu menu = requireMenu(flavor);
        currentItem = new OrderItem(menu, new ServingOption(ServingOption.Type.CUP));
    }

    public void setFlavor(String flavor) {
        Menu menu = requireMenu(flavor);
        if (currentItem == null) {
            currentItem = new OrderItem(menu, new ServingOption(ServingOption.Type.CUP));
        } else {
            currentItem.changeMenu(menu);
        }
    }

    public String getFlavor() {
        if (currentItem == null) return null;
        return currentItem.getFlavor();
    }

    public void setServingOption(ServingOption.Type type) {
        requireCurrentItem();
        currentItem.changeServingOption(new ServingOption(type));
    }

    public String getServingOption() {
        if (currentItem == null) return null;
        return currentItem.getServingOption().getOptionName();
    }

    public void addTopping(ToppingOption.Type type) {
        requireCurrentItem();
        currentItem.addTopping(new ToppingOption(type));
    }

    public void removeTopping(ToppingOption.Type type) {
        requireCurrentItem();
        currentItem.removeTopping(new ToppingOption(type));
    }

    public void clearToppings() {
        requireCurrentItem();
        currentItem.clearToppings();
    }

    public List<ToppingOption> getCurrentToppings() {
        requireCurrentItem();
        return currentItem.getToppings();
    }

    public int getCurrentItemPrice() {
        requireCurrentItem();
        return currentItem.getTotalPrice();
    }

    public void addCurrentItemToCart() {
        requireCurrentItem();
        cart.addItem(currentItem);
        currentItem = null;
    }

    public List<OrderItem> getCartItems() {
        return cart.getCartItems();
    }

    public int getTotalPrice() {
        return cart.getTotalPrice();
    }

    public void removeCartItem(int index) {
        cart.removeItem(index);
    }

    public void clearCart() {
        cart.clear();
        currentItem = null;
    }

    public boolean isCartEmpty() {
        return cart.isEmpty();
    }

    public OrderItem getCurrentItem() {
        return currentItem;
    }

    private Menu requireMenu(String flavor) {
        if (flavor == null || flavor.trim().isEmpty()) {
            throw new IllegalArgumentException("flavor는 비어 있을 수 없습니다.");
        }
        Menu menu = menuCatalog.findByName(flavor.trim());
        if (menu == null) {
            throw new IllegalArgumentException("존재하지 않는 메뉴입니다: " + flavor);
        }
        return menu;
    }

    private void requireCurrentItem() {
        if (currentItem == null) {
            throw new IllegalStateException("현재 선택 중인 주문 항목이 없습니다. 먼저 flavor를 선택하세요.");
        }
    }
}
