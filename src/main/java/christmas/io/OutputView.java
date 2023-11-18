package christmas.io;

import static christmas.io.OutputMessage.AMOUNT_AFTER_DISCOUNT;
import static christmas.io.OutputMessage.AMOUNT_BEFORE_DISCOUNT;
import static christmas.io.OutputMessage.BENEFITS_DETAIL;
import static christmas.io.OutputMessage.EVENT_BADGE;
import static christmas.io.OutputMessage.GIVEAWAY_MENU;
import static christmas.io.OutputMessage.ORDERED_MENU;
import static christmas.io.OutputMessage.READ_DATE_MESSAGE;
import static christmas.io.OutputMessage.READ_MENU_MESSAGE;
import static christmas.io.OutputMessage.SHOW_EVENT_MESSAGE;
import static christmas.io.OutputMessage.TOTAL_BENEFITS_AMOUNT;
import static christmas.io.OutputMessage.WELCOME_MESSAGE;

public class OutputView {

    public OutputView() {
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE.getMessage());
    }

    public void printReadDateMessage() {
        System.out.println(READ_DATE_MESSAGE.getMessage());
    }

    public void printReadMenuMessage() {
        System.out.println(READ_MENU_MESSAGE.getMessage());
    }

    public void printAllMenus(String menus) {
        System.out.println(SHOW_EVENT_MESSAGE.getMessage() + "\n");
        System.out.println(ORDERED_MENU.getMessage());
        System.out.println(menus);
    }

    public void printTotalPriceBeforeDiscount(String totalPrice) {
        System.out.println(AMOUNT_BEFORE_DISCOUNT.getMessage());
        System.out.println(totalPrice);
    }

    public void printGiveawayEvent(String giveawayMenu) {
        System.out.println(GIVEAWAY_MENU.getMessage());
        System.out.println(giveawayMenu);
    }

    public void printBenefitsDetail(String benefitsDetail) {
        System.out.println(BENEFITS_DETAIL.getMessage());
        System.out.println(benefitsDetail);
    }

    public void printTotalBenefitsAmount(String totalBenefitsAmount) {
        System.out.println(TOTAL_BENEFITS_AMOUNT.getMessage());
        System.out.println(totalBenefitsAmount);
    }

    public void printTotalPriceAfterDiscount(String totalPriceAfterDiscount) {
        System.out.println(AMOUNT_AFTER_DISCOUNT.getMessage());
        System.out.println(totalPriceAfterDiscount);
    }

    public void printEventBadge(String eventBadge) {
        System.out.println(EVENT_BADGE.getMessage());
        System.out.println(eventBadge);
    }

    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
