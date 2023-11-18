package christmas.model.event;

import static christmas.error.ErrorMessage.INVALID_DATE;

public class Day {
    private final int day;

    private Day(int day) {
        this.day = day;
    }

    public static Day of(int day) {
        validateDay(day);
        return new Day(day);
    }

    public int getDay() {
        return this.day;
    }

    public static void validateDay(int day) {
        if (day <= 0 || day > 31) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }
}
