// src/model/Menu.java
package model;

import java.util.Objects;

public class Menu {
    private final String menuName;
    private final int price;

    public Menu(String menuName, int price) {
        if (menuName == null || menuName.trim().isEmpty()) {
            throw new IllegalArgumentException("menuName은 비어 있을 수 없습니다.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("price는 0 이상이어야 합니다.");
        }
        this.menuName = menuName.trim();
        this.price = price;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return menuName + " (" + price + "원)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return price == menu.price && menuName.equals(menu.menuName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuName, price);
    }
}
