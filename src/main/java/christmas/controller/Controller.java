package christmas.controller;

import static christmas.service.EventBadgeService.getEventBadgeByTotalAmount;

import christmas.io.IOManager;
import christmas.model.event.Day;
import christmas.model.event.badge.EventBadge;
import christmas.model.menu.Menus;
import christmas.model.money.Money;
import christmas.service.DiscountEventService;
import christmas.service.GiveawayEventService;

public class Controller {

    private final IOManager ioManager;
    private final DiscountEventService discountEventService;
    private final GiveawayEventService giveawayEventService;

    public Controller(IOManager ioManager, DiscountEventService discountEventService,
                      GiveawayEventService giveawayEventService) {
        this.ioManager = ioManager;
        this.discountEventService = discountEventService;
        this.giveawayEventService = giveawayEventService;
    }

    public void run() {
        Day reservationDay = ioManager.readArrivalDate();
        Menus menus = ioManager.readMenus();
        Money totalPrice = getTotalPrice(menus);
        Money giveawayAmount = getGiveAwayAmount();
        ioManager.printGiveawayEvent(giveawayEventService.getHistory());

        discountEventService.applyDiscount(reservationDay, menus);
        ioManager.printBenefitsDetail(discountEventService.getDiscountEventHistory(), giveawayEventService.getHistory());

        Money discountAmount = Money.of(discountEventService.getTotalDiscountAmount().getAmount());
        ioManager.printTotalBenefitsAmount(Money.of(discountAmount.getAmount() + giveawayAmount.getAmount()));

        EventBadge eventBadge = getEventBadgeByTotalAmount(totalPrice);
        ioManager.printTotalPriceAfterDiscount(Money.of(totalPrice.getAmount() - discountAmount.getAmount()));

        ioManager.printEventBadge(eventBadge);
    }

    private Money getTotalPrice(Menus menus) {
        ioManager.printAllMenus(menus);
        Money totalPrice = Money.of(menus.getTotalPrice());
        ioManager.printTotalPriceBeforeDiscount(totalPrice);
        giveawayEventService.applyEvent(totalPrice);
        return totalPrice;
    }

    private Money getGiveAwayAmount() {
        return giveawayEventService.getGiveawayEventAmount();
    }
}
