package christmas.service;

import static christmas.model.event.discount.DiscountEventConstant.D_DAY_DISCOUNT_DAILY_DISCOUNT_AMOUNT;
import static christmas.model.event.discount.DiscountEventConstant.D_DAY_DISCOUNT_INIT_DISCOUNT_AMOUNT;
import static christmas.model.event.discount.DiscountEventConstant.SPECIAL_DISCOUNT_AMOUNT;
import static christmas.model.event.discount.DiscountEventConstant.WEEKDAY_DISCOUNT_AMOUNT;
import static christmas.model.event.discount.DiscountEventConstant.WEEKEND_DISCOUNT_AMOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.event.Day;
import christmas.model.event.discount.DiscountEventHistory;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import christmas.model.menu.Menus;
import christmas.model.money.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountEventServiceTest {

    private static final String menusRawString = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
    private static final Money totalPriceBeforeDiscount = Money.of(142000);
    private static final String delimiter = ",";
    static int mainMenuCount = 0;
    static int desertMenuCount= 0;

    private DiscountEventService discountEventService;
    private Menus menus;

    @BeforeAll
    static void setUpOnce() {
        String[] splitMenus = menusRawString.split(delimiter);
        for (String s: splitMenus) {
            String[] splitMenu = s.split("-");
            MenuType menuType = Menu.valueOf(splitMenu[0]).getMenuType();
            if (menuType == MenuType.MAIN) {
                mainMenuCount += Integer.parseInt(splitMenu[1]);
            } else if (menuType == MenuType.DESSERT) {
                desertMenuCount += Integer.parseInt(splitMenu[1]);
            }
        }
    }

    @BeforeEach
    void setUp() {
        menus = Menus.fromString(menusRawString, delimiter);
        discountEventService = DiscountEventService.of();
    }

    @DisplayName("할인 이벤트가 적용되지 않은 경우 할인된 금액은 0원이다.")
    @Test
    void noDiscountTest() {
        // given
        Day noDiscountDay = Day.of(31);

        // when
        Money totalDiscountAmount = discountEventService.getTotalDiscountAmount();

        // then
        assertThat(totalDiscountAmount.getAmount()).isEqualTo(0);
    }

    @DisplayName("평일의 경우 평일 할인이 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void weekDayDiscountEventApplyTest(int weekDayday) {
        // given
        Day weekDay = Day.of(weekDayday);

        // when
        discountEventService.applyDiscount(weekDay, menus);
        DiscountEventHistory history = discountEventService.getDiscountEventHistory();

        // then
        assertThat(history.getDetails().containsKey(WEEKDAY_DISCOUNT_AMOUNT))
                .isTrue();
    }

    @DisplayName("평일 할인은 메인 메뉴의 개수만큼 할인이 비례적으로 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void weekDayDiscountEventAmountTest(int weekDayday) {
        // given
        Day weekDay = Day.of(weekDayday);

        // when
        discountEventService.applyDiscount(weekDay, menus);
        DiscountEventHistory history = discountEventService.getDiscountEventHistory();
        int weekDayDiscountAmount = WEEKDAY_DISCOUNT_AMOUNT.getValue() * mainMenuCount;

        // then
        assertThat(history.getDetails().get(WEEKDAY_DISCOUNT_AMOUNT))
                .isEqualTo(weekDayDiscountAmount);
    }

    @DisplayName("주말의 경우 주말 할인이 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void weekendDiscountEventApplyTest(int weekendDay) {
        // given
        Day weekend = Day.of(weekendDay);

        // when
        discountEventService.applyDiscount(weekend, menus);
        DiscountEventHistory history = discountEventService.getDiscountEventHistory();
        int weekendDiscountAmount = WEEKEND_DISCOUNT_AMOUNT.getValue() * desertMenuCount;

        // then
        assertThat(history.getDetails().containsKey(WEEKEND_DISCOUNT_AMOUNT))
                .isTrue();
    }

    @DisplayName("주말 할인은 디저트 메뉴의 개수만큼 할인이 비례적으로 적용된다..")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void weekendDiscountEventAmountTest(int weekendDay) {
        // given
        Day weekend = Day.of(weekendDay);

        // when
        discountEventService.applyDiscount(weekend, menus);
        DiscountEventHistory history = discountEventService.getDiscountEventHistory();
        int weekendDiscountAmount = WEEKEND_DISCOUNT_AMOUNT.getValue() * desertMenuCount;

        // then
        assertThat(history.getDetails().get(WEEKEND_DISCOUNT_AMOUNT))
                .isEqualTo(weekendDiscountAmount);
    }

    @DisplayName("별이 붙은 날의 경우 특별 할인이 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void specialDiscountApplyTest(int special) {
        // given
        Day specialDay = Day.of(special);

        // when
        discountEventService.applyDiscount(specialDay, menus);
        DiscountEventHistory history = discountEventService.getDiscountEventHistory();

        // then
        assertThat(history.getDetails().containsKey(SPECIAL_DISCOUNT_AMOUNT))
                .isTrue();
    }

    @DisplayName("25일 이전까지는 크리스마스 디데이 할인이 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 25})
    void christmasDDayDiscountApplyTest(int dday) {
        // given
        Day dDay = Day.of(dday);

        // when
        discountEventService.applyDiscount(dDay, menus);
        DiscountEventHistory history = discountEventService.getDiscountEventHistory();

        // then
        assertThat(history.getDetails().containsKey(D_DAY_DISCOUNT_DAILY_DISCOUNT_AMOUNT))
                .isTrue();
    }

    @DisplayName("디데이 할인 금액은 1000 + (날짜 - 1) * 100원이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 25})
    void christmasDDayDiscountAmountTest(int dday) {
        // given
        Day dDay = Day.of(dday);

        // when
        discountEventService.applyDiscount(dDay, menus);
        DiscountEventHistory history = discountEventService.getDiscountEventHistory();
        int discountAmount = D_DAY_DISCOUNT_INIT_DISCOUNT_AMOUNT.getValue()
                + (dday - 1) * D_DAY_DISCOUNT_DAILY_DISCOUNT_AMOUNT.getValue();

        // then
        assertThat(history.getDetails().get(D_DAY_DISCOUNT_DAILY_DISCOUNT_AMOUNT))
                .isEqualTo(discountAmount);
    }
}