package concurrentcore.collectors;

public class Production {
    private final String name;
    private final double price;

    public Production(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
