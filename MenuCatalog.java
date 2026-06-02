// src/model/MenuCatalog.java
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuCatalog {
    private final List<Menu> menus;

    public MenuCatalog() {
        this.menus = new ArrayList<>();
    }

    public void addMenu(Menu menu) {
        if (menu == null) throw new IllegalArgumentException("menu는 null일 수 없습니다.");
        menus.add(menu);
    }

    public List<Menu> getMenus() {
        return Collections.unmodifiableList(menus);
    }

    public Menu findByName(String name) {
        if (name == null) return null;
        for (Menu menu : menus) {
            if (menu.getMenuName().equalsIgnoreCase(name.trim())) {
                return menu;
            }
        }
        return null;
    }

    public static MenuCatalog defaultCatalog() {
        MenuCatalog c = new MenuCatalog();
        c.addMenu(new Menu("choco", 3500));
        c.addMenu(new Menu("vanilla", 3500));
        c.addMenu(new Menu("strawberry", 3500));
        return c;
    }
}
