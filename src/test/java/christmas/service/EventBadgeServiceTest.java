package christmas.service;

import static christmas.model.event.EventBadge.NONE;
import static christmas.model.event.EventBadge.SANTA;
import static christmas.model.event.EventBadge.STAR;
import static christmas.model.event.EventBadge.TREE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.event.EventBadge;
import christmas.model.money.Money;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventBadgeServiceTest {

    private static final int[] totalPrices = {0, 5000, 10000, 200000};
    private static final EventBadge[] eventBadges = {NONE, STAR, TREE, SANTA};

    @DisplayName("총 주문 금액에 따라 이벤트 배지를 정상적으로 반환받는다.")
    @Test
    void eventBadgeTest() {
        // given
        int[] prices = totalPrices;
        EventBadge[] expected = eventBadges;
        List<EventBadge> actual = new ArrayList<>();

        // when
        for (int totalPrice : prices) {
            actual.add(EventBadgeService.getEventBadgeByTotalAmount(Money.of(totalPrice)));
        }

        // then
        assertThat(expected)
                .usingRecursiveComparison()
                .isEqualTo(actual.toArray());
    }
}