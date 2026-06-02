// src/model/OrderItem.java
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderItem {
    private Menu menu;
    private ServingOption servingOption;
    private final List<ToppingOption> toppings;
    private int totalPrice;

    public OrderItem(Menu menu, ServingOption servingOption) {
        if (menu == null) throw new IllegalArgumentException("menu는 null일 수 없습니다.");
        if (servingOption == null) throw new IllegalArgumentException("servingOption은 null일 수 없습니다.");
        this.menu = menu;
        this.servingOption = servingOption;
        this.toppings = new ArrayList<>();
        recalculateTotalPrice();
    }

    public Menu getMenu() {
        return menu;
    }

    public String getMenuName() {
        return menu.getMenuName();
    }

    public String getFlavor() {
        return menu.getMenuName();
    }

    public void setFlavor(String flavor) {
        if (flavor == null || flavor.trim().isEmpty()) {
            throw new IllegalArgumentException("flavor는 비어 있을 수 없습니다.");
        }
        this.menu = new Menu(flavor.trim(), this.menu.getPrice());
        recalculateTotalPrice();
    }

    public void changeMenu(Menu newMenu) {
        if (newMenu == null) throw new IllegalArgumentException("newMenu는 null일 수 없습니다.");
        this.menu = newMenu;
        recalculateTotalPrice();
    }

    public ServingOption getServingOption() {
        return servingOption;
    }

    public List<ToppingOption> getToppings() {
        return Collections.unmodifiableList(toppings);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void changeServingOption(ServingOption servingOption) {
        if (servingOption == null) throw new IllegalArgumentException("servingOption은 null일 수 없습니다.");
        this.servingOption = servingOption;
        recalculateTotalPrice();
    }

    public void addTopping(ToppingOption topping) {
        if (topping == null) throw new IllegalArgumentException("topping은 null일 수 없습니다.");
        toppings.add(topping);
        recalculateTotalPrice();
    }

    public void removeTopping(ToppingOption topping) {
        if (topping == null) return;
        toppings.remove(topping);
        recalculateTotalPrice();
    }

    public void clearToppings() {
        toppings.clear();
        recalculateTotalPrice();
    }

    private void recalculateTotalPrice() {
        int sum = menu.getPrice() + servingOption.getExtraPrice();
        for (ToppingOption topping : toppings) {
            sum += topping.getExtraPrice();
        }
        this.totalPrice = sum;
    }

    public String toppingsSummary() {
        if (toppings.isEmpty()) return "없음";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < toppings.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(toppings.get(i).getOptionName());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "OrderItem{menu=" + getMenuName()
                + ", servingOption=" + servingOption.getOptionName()
                + ", toppings=" + toppingsSummary()
                + ", totalPrice=" + totalPrice + "원}";
    }
}
