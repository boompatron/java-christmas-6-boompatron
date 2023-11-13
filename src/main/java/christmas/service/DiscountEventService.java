package christmas.service;

import static christmas.model.event.discount.DiscountEventConstant.D_DAY_DISCOUNT_DAILY_DISCOUNT_AMOUNT;
import static christmas.model.event.discount.DiscountEventConstant.D_DAY_DISCOUNT_INIT_DISCOUNT_AMOUNT;
import static christmas.model.event.discount.DiscountEventConstant.SPECIAL_DISCOUNT_AMOUNT;
import static christmas.model.event.discount.DiscountEventConstant.WEEKDAY_DISCOUNT_AMOUNT;
import static christmas.model.event.discount.DiscountEventConstant.WEEKEND_DISCOUNT_AMOUNT;
import static christmas.model.menu.MenuType.DESSERT;
import static christmas.model.menu.MenuType.MAIN;

import christmas.model.event.Day;
import christmas.model.event.discount.DiscountEventHistory;
import christmas.model.menu.MenuType;
import christmas.model.menu.Menus;
import christmas.model.money.Money;
import java.util.Set;

public class DiscountEventService {

    private static final Set<Integer> STAR_DAYS = Set.of(3, 10, 17, 24, 25, 31);
    private static final Set<MenuType> WEEKDAY_DISCOUNT_MENU_TYPES = Set.of(MAIN);
    private static final Set<MenuType> WEEKEND_DISCOUNT_MENU_TYPES = Set.of(DESSERT);
    private static final int CHRISTMAS_DAY = 25;

    private final Money totalDiscountAmount;
    private final DiscountEventHistory discountEventHistory;

    private DiscountEventService() {
        totalDiscountAmount = Money.of(0);
        this.discountEventHistory = new DiscountEventHistory();
    }

    public static DiscountEventService of() {
        return new DiscountEventService();
    }

    public void applyDiscount(Day reservationDay, Menus menus) {
        applyChristmasDDayDiscount(reservationDay);
        applyWeekOrWeekendDiscount(menus, reservationDay);
        applySpecialDiscount(reservationDay);
    }

    public DiscountEventHistory getDiscountEventHistory() {
        return discountEventHistory;
    }

    public Money getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    private void applyChristmasDDayDiscount(Day reservationDay) {
        int discountAmount = 0;
        if (reservationDay.getDay() <= CHRISTMAS_DAY) {
            discountAmount = getDDayDiscountAmount(reservationDay.getDay());
        }

        if (discountAmount > 0) {
            totalDiscountAmount.addAmount(discountAmount);
            discountEventHistory.addDiscountEvent(D_DAY_DISCOUNT_DAILY_DISCOUNT_AMOUNT, discountAmount);
        }
    }

    private void applyWeekOrWeekendDiscount(Menus menus, Day reservationDay) {
        int discountAmount = 0;
        Set<MenuType> discountMenuTypes = WEEKDAY_DISCOUNT_MENU_TYPES;
        boolean isWeekend = isDayWeekEnd(reservationDay.getDay());
        if (isWeekend) {
            discountMenuTypes = WEEKEND_DISCOUNT_MENU_TYPES;
        }

        discountAmount = calculateWeekDiscount(menus, discountMenuTypes, isWeekend);
        if (discountAmount > 0) {
            totalDiscountAmount.addAmount(discountAmount);
            if (isWeekend) {
                discountEventHistory.addDiscountEvent(WEEKEND_DISCOUNT_AMOUNT, discountAmount);
                return;
            }
            discountEventHistory.addDiscountEvent(WEEKDAY_DISCOUNT_AMOUNT, discountAmount);
        }
    }

    private void applySpecialDiscount(Day reservationDay) {
        int discountAmount = 0;
        if (isStarOnDay(reservationDay.getDay())) {
            discountAmount = SPECIAL_DISCOUNT_AMOUNT.getValue();

            totalDiscountAmount.addAmount(discountAmount);
            discountEventHistory.addDiscountEvent(SPECIAL_DISCOUNT_AMOUNT, discountAmount);
        }
    }

    private boolean isDayWeekEnd(int day) {
        return day % 7 == 1 || day % 7 == 2;
    }

    private int getDDayDiscountAmount(int day) {
        return (day - 1) * D_DAY_DISCOUNT_DAILY_DISCOUNT_AMOUNT.getValue()
                + D_DAY_DISCOUNT_INIT_DISCOUNT_AMOUNT.getValue();
    }

    private boolean isStarOnDay(int day) {
        return STAR_DAYS.contains(day);
    }

    private int calculateWeekDiscount(Menus menus, Set<MenuType> discountMenuTypes, boolean isWeekEnd) {
        int discountMenuCount = 0;
        for (MenuType discountMenuType: discountMenuTypes) {
            discountMenuCount += menus.getCountByMenuType(discountMenuType);
        }

        if (isWeekEnd) {
            return discountMenuCount * WEEKEND_DISCOUNT_AMOUNT.getValue();
        }
        return discountMenuCount * WEEKDAY_DISCOUNT_AMOUNT.getValue();
    }
}
