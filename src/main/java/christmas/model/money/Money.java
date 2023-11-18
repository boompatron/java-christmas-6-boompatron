package christmas.model.money;

import static christmas.error.ErrorMessage.MONEY_ONLY_POSITIVE;

public class Money {

    private int amount;

    private Money(int amount) {
        this.amount = amount;
    }

    public static Money of(int amount) {
        validateMoney(amount);
        return new Money(amount);
    }

    public void addAmount(int amount) {
        validateMoney(amount);
        this.amount += amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public static void validateMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(MONEY_ONLY_POSITIVE.getMessage());
        }
    }
}
