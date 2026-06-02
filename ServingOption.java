/**
 * ServingOption enum
 * - 컵/콘 선택 정보를 관리합니다.
 * - 콘은 500원이 추가되도록 extraPrice를 둡니다.
 */
public enum ServingOption {
    CUP("컵", 0),
    CONE("콘", 500);

    private final String label;
    private final int extraPrice;

    ServingOption(String label, int extraPrice) {
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
