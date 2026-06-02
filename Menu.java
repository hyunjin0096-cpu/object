/**
 * Menu 클래스
 * - 아이스크림의 기본 메뉴 정보를 저장하는 Model 클래스입니다.
 * - Controller는 사용자가 선택한 버튼에 따라 Menu 객체를 생성하거나 저장합니다.
 */
public class Menu {
    private String menuName; // 메뉴 이름: choco, vanilla, strawberry
    private int price;       // 기본 가격: 3500원

    public Menu(String menuName, int price) {
        this.menuName = menuName;
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
}
