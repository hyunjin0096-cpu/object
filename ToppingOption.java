/**
 * ToppingOption enum
 * - 추가 토핑 정보를 관리합니다.
 * - 토핑명과 추가 가격을 함께 저장합니다.
 */
public enum ToppingOption {
    CHOCOCHIP("초코칩", 1000),
    HONEY("연유", 500),
    CEREAL("시리얼", 1000);

    private final String label;
    private final int extraPrice;

    ToppingOption(String label, int extraPrice) {
        this.label = label;
        this.extraPrice = extraPrice;
    }

    public String getLabel() {
        return label;
    }

    public int getExtraPrice() {
        return extraPrice;
    }
}
