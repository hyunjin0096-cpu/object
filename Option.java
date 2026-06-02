// src/model/Option.java
package model;

import java.util.Objects;

public abstract class Option {
    private final String optionName;
    private final int extraPrice;

    protected Option(String optionName, int extraPrice) {
        if (optionName == null || optionName.trim().isEmpty()) {
            throw new IllegalArgumentException("optionName은 비어 있을 수 없습니다.");
        }
        if (extraPrice < 0) {
            throw new IllegalArgumentException("extraPrice는 0 이상이어야 합니다.");
        }
        this.optionName = optionName.trim();
        this.extraPrice = extraPrice;
    }

    public String getOptionName() {
        return optionName;
    }

    public int getExtraPrice() {
        return extraPrice;
    }

    @Override
    public String toString() {
        return optionName + " (+" + extraPrice + "원)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option)) return false;
        Option option = (Option) o;
        return extraPrice == option.extraPrice && optionName.equals(option.optionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionName, extraPrice);
    }
}
