package christmas.service;

import static christmas.model.event.GiveawayEventConstant.GIVEAWAY_EVENT;

import christmas.model.event.GiveawayEventHistory;

public class GiveawayEventService {

    private final static int EVENT_AMOUNT = 1;

    private final GiveawayEventHistory history;

    private GiveawayEventService() {
        this.history = new GiveawayEventHistory();
    }

    public static GiveawayEventService of() {
        return new GiveawayEventService();
    }

    public void applyEvent() {
        applyGiveawayEvent();
    }

    public GiveawayEventHistory getHistory() {
        return history;
    }

    private void applyGiveawayEvent() {
        history.addGiveawayEvent(GIVEAWAY_EVENT, EVENT_AMOUNT);
    }
}
