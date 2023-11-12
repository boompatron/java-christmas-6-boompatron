package christmas.model.event;

public enum EventBadge {

    NONE(0, "없음"),
    STAR(5000, "별"),
    TREE(10000, "트리"),
    SANTA(20000, "산타");

    private final int price;
    private final String name;

    EventBadge(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }
}
