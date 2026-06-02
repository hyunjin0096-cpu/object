// src/model/ToppingOption.java
package model;

public class ToppingOption extends Option {
    public enum Type {
        CHOCOCHIP("chocochip", 1000),
        HONEY("honey", 500),
        CEREAL("cereal", 1000);

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

    public ToppingOption(Type type) {
        super(type.getLabel(), type.getPrice());
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
