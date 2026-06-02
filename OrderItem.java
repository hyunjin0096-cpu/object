import java.util.ArrayList;
import java.util.List;

/**
 * OrderItem 클래스
 * - 사용자가 선택한 하나의 주문 항목을 나타냅니다.
 * - 메뉴 + 컵/콘 옵션 + 토핑 목록 + 최종 금액을 하나로 묶습니다.
 */
public class OrderItem {
    private Menu menu;
    private ServingOption servingOption;
    private List<ToppingOption> toppings;
    private int totalPrice;

    public OrderItem(Menu menu, ServingOption servingOption, List<ToppingOption> toppings) {
        this.menu = menu;
        this.servingOption = servingOption;
        this.toppings = new ArrayList<>(toppings);
        this.totalPrice = calculateTotalPrice();
    }

    /**
     * 메뉴 기본 가격 + 컵/콘 추가 가격 + 토핑 추가 가격을 계산합니다.
     */
    private int calculateTotalPrice() {
        int sum = menu.getPrice();
        sum += servingOption.getExtraPrice();

        for (ToppingOption topping : toppings) {
            sum += topping.getExtraPrice();
        }

        return sum;
    }

    public Menu getMenu() {
        return menu;
    }

    public ServingOption getServingOption() {
        return servingOption;
    }

    public List<ToppingOption> getToppings() {
        return toppings;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * 장바구니 화면에 출력하기 좋은 문자열 형태로 주문 항목을 반환합니다.
     */
    public String toDisplayText() {
        StringBuilder sb = new StringBuilder();
        sb.append(menu.getMenuName()).append(" / ");
        sb.append(servingOption.getLabel());

        if (!toppings.isEmpty()) {
            sb.append(" / 토핑: ");
            for (int i = 0; i < toppings.size(); i++) {
                sb.append(toppings.get(i).getLabel());
                if (i < toppings.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            sb.append(" / 토핑 없음");
        }

        sb.append(" / ").append(totalPrice).append("원");
        return sb.toString();
    }
}
