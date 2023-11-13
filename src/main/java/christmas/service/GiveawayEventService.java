package christmas.service;

import static christmas.model.event.giveaway.GiveawayEventConstant.GIVEAWAY_EVENT;

import christmas.model.event.giveaway.GiveawayEventConstant;
import christmas.model.event.giveaway.GiveawayEventHistory;
import christmas.model.money.Money;

public class GiveawayEventService {

    private final static int EVENT_AMOUNT = 1;

    private final GiveawayEventHistory history;

    private GiveawayEventService() {
        this.history = new GiveawayEventHistory();
    }

    public static GiveawayEventService of() {
        return new GiveawayEventService();
    }

    public void applyEvent(Money totalAmount) {
        applyGiveawayEvent(totalAmount.getAmount());
    }

    public GiveawayEventHistory getHistory() {
        return history;
    }

    public Money getGiveawayEventAmount() {
        int amount = 0;
        for (GiveawayEventConstant constant: history.getDetails().keySet()) {
            amount += history.getDetails().get(constant) * constant.getEventMenu().getPrice();
        }
        return Money.of(amount);
    }

    private void applyGiveawayEvent(int amount) {
        if (amount > GIVEAWAY_EVENT.getThreshold()) {
            history.addGiveawayEvent(GIVEAWAY_EVENT, EVENT_AMOUNT);
        }
    }
}
