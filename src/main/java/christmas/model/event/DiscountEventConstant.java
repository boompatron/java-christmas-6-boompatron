package christmas.model.event;

public enum DiscountEventConstant {

    D_DAY_DISCOUNT_DAILY_DISCOUNT_AMOUNT("크리스마스 디데이 할인", 100),
    D_DAY_DISCOUNT_INIT_DISCOUNT_AMOUNT("크리스마스 디데이 할인", 1000),
    WEEKDAY_DISCOUNT_AMOUNT("평일 할인", 2023),
    WEEKEND_DISCOUNT_AMOUNT("주말 할인", 2023),
    SPECIAL_DISCOUNT_AMOUNT("특별 할인", 1000),
    ;

    private final String name;
    private final int value;

    DiscountEventConstant(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
