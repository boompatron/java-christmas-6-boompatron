package christmas.service;

import static christmas.model.event.GiveawayEventConstant.GIVEAWAY_EVENT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.event.GiveawayEventHistory;
import christmas.model.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiveawayEventServiceTest {

    private static final Money overThresholdAmount = Money.of(GIVEAWAY_EVENT.getThreshold() + 1000);
    private static final Money underThresholdAmount = Money.of(GIVEAWAY_EVENT.getThreshold() - 1000);

    private GiveawayEventService giveawayEventService;

    @BeforeEach
    void setUp() {
        giveawayEventService = GiveawayEventService.of();
    }

    @DisplayName("총 주문 금액이 12만원 이상일 경우 샴페인이 제공된다.")
    @Test
    void giveawayApplyTest() {
        // given
        Money money = overThresholdAmount;

        // when
        giveawayEventService.applyEvent(money);
        GiveawayEventHistory history = giveawayEventService.getHistory();

        // then
        assertThat(history.getDetails().containsKey(GIVEAWAY_EVENT)).isTrue();
    }

    @DisplayName("총 주문 금액이 12만원 이하면 증정품은 제공되지 않는다.")
    @Test
    void giveawayNotApplyTest() {
        // given
        Money money = underThresholdAmount;

        // when
        giveawayEventService.applyEvent(money);
        GiveawayEventHistory history = giveawayEventService.getHistory();

        // then
        assertThat(history.getDetails().containsKey(GIVEAWAY_EVENT)).isFalse();
    }
}