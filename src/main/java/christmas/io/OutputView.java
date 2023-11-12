package christmas.io;

import static christmas.io.IOConstant.WELCOME_MESSAGE;

public class OutputView {

    public OutputView() {
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE.getValue());
    }

    public void printReadDateMessage() {
        System.out.println(IOConstant.READ_DATE_MESSAGE.getValue());
    }

    public void printReadMenuMessage() {
        System.out.println(IOConstant.READ_MENU_MESSAGE.getValue());
    }

    public void printAllMenus(String menus) {
        System.out.println(IOConstant.SHOW_EVENT_MESSAGE.getValue() + "\n");
        System.out.println(IOConstant.ORDERED_MENU.getValue());
        System.out.println(menus);
    }

    public void printTotalPriceBeforeDiscount(String totalPrice) {
        System.out.println(IOConstant.AMOUNT_BEFORE_DISCOUNT.getValue());
        System.out.println(totalPrice);
    }

    public void printGiveawayEvent(String giveawayMenu) {
        System.out.println(IOConstant.GIVEAWAY_MENU.getValue());
        System.out.println(giveawayMenu);
    }

    public void printBenefitsDetail(String benefitsDetail) {
        System.out.println(IOConstant.BENEFITS_DETAIL.getValue());
        System.out.println(benefitsDetail);
    }

    public void printTotalBenefitsAmount(String totalBenefitsAmount) {
        System.out.println(IOConstant.TOTAL_BENEFITS_AMOUNT.getValue());
        System.out.println(totalBenefitsAmount);
    }

    public void printTotalPriceAfterDiscount(String totalPriceAfterDiscount) {
        System.out.println(IOConstant.AMOUNT_AFTER_DISCOUNT.getValue());
        System.out.println(totalPriceAfterDiscount);
    }

    public void printEventBadge(String eventBadge) {
        System.out.println(IOConstant.EVENT_BADGE.getValue());
        System.out.println(eventBadge);
    }
}
