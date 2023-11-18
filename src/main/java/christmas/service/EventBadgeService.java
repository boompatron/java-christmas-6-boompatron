package christmas.service;

import christmas.model.event.badge.EventBadge;
import christmas.model.money.Money;

public class EventBadgeService {

    private EventBadgeService() {
    }

    public static EventBadge getEventBadgeByTotalAmount(Money eventAmount) {
        int amount = eventAmount.getAmount();
        if (amount >= EventBadge.SANTA.getPrice()) {
            return EventBadge.SANTA;
        }
        if (amount >= EventBadge.TREE.getPrice()) {
            return EventBadge.TREE;
        }
        if (amount >= EventBadge.STAR.getPrice()) {
            return EventBadge.STAR;
        }
        return EventBadge.NONE;
    }
}
