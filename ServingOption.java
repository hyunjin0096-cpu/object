// src/model/ServingOption.java
package model;

public class ServingOption extends Option {
    public enum Type {
        CUP("cup", 0),
        CONE("cone", 500);

        private final String label;
        private final int price;

        Type(String label, int price) {
            this.label = label;
            this.price = price;
        }

        public String getLabel() {
            return label;
        }

        public int getPrice() {
            return price;
        }
    }

    private final Type type;

    public ServingOption(Type type) {
        super(type.getLabel(), type.getPrice());
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
