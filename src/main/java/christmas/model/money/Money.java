package christmas.model.money;

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
            throw new IllegalArgumentException();
        }
    }
}
