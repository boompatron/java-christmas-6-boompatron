package christmas.controller;

import static christmas.service.EventBadgeService.getEventBadgeByTotalAmount;

import christmas.io.IOManager;
import christmas.model.event.Day;
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
        printAllMenus(menus);
        printTotalPriceBeforeDiscount(menus);
        applyAllEvents(menus, reservationDay);
        printGiveawayEvent();
        printBenefitsDetail();
        printTotalBenefitsAmount();
        printTotalPriceAfterDiscount(menus);
        printEventBadge(menus);
    }

    private void printAllMenus(Menus menus) {
        ioManager.printAllMenus(menus);
    }

    private void printTotalPriceBeforeDiscount(Menus menus) {
        ioManager.printTotalPriceBeforeDiscount(Money.of(menus.getTotalPrice()));
    }

    private void printGiveawayEvent() {
        ioManager.printGiveawayEvent(giveawayEventService.getHistory());
    }

    private void printBenefitsDetail() {
        ioManager.printBenefitsDetail(discountEventService.getDiscountEventHistory(),
                giveawayEventService.getHistory());
    }

    private void printTotalBenefitsAmount() {
        Money discountAmount = Money.of(discountEventService.getTotalDiscountAmount().getAmount());
        Money giveawayAmount = Money.of(giveawayEventService.getGiveawayEventAmount().getAmount());
        ioManager.printTotalBenefitsAmount(Money.of(discountAmount.getAmount() + giveawayAmount.getAmount()));
    }

    private void printTotalPriceAfterDiscount(Menus menus) {
        Money totalAfterDiscount = Money.of(
                menus.getTotalPrice() - discountEventService.getTotalDiscountAmount().getAmount());
        ioManager.printTotalPriceAfterDiscount(totalAfterDiscount);
    }

    private void printEventBadge(Menus menus) {
        ioManager.printEventBadge(getEventBadgeByTotalAmount(Money.of(menus.getTotalPrice())));
    }

    private void applyAllEvents(Menus menus, Day reservationDay) {
        applyGiveawayEvent(Money.of(menus.getTotalPrice()));
        applyAllDiscountEvents(reservationDay, menus);
    }

    private void applyGiveawayEvent(Money totalPrice) {
        giveawayEventService.applyEvent(totalPrice);
    }

    private void applyAllDiscountEvents(Day reservationDay, Menus menus) {
        discountEventService.applyChristmasDDayDiscount(reservationDay);
        discountEventService.applyWeekOrWeekendDiscount(menus, reservationDay);
        discountEventService.applySpecialDiscount(reservationDay);
    }
}
