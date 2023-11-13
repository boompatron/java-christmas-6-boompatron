package christmas.io;

import static christmas.io.IOConstant.DISCOUNT_DELIMITER;
import static christmas.io.IOConstant.INPUT_DELIMITER;
import static christmas.io.IOConstant.MENU_UNIT;
import static christmas.io.IOConstant.MINUS;
import static christmas.io.IOConstant.NONE;
import static christmas.io.OutputMessage.GIVEAWAY_EVENT;
import static christmas.util.StringUtils.convertIntegerToStringWithComma;

import christmas.model.event.Day;
import christmas.model.event.badge.EventBadge;
import christmas.model.event.discount.DiscountEventConstant;
import christmas.model.event.discount.DiscountEventHistory;
import christmas.model.event.giveaway.GiveawayEventConstant;
import christmas.model.event.giveaway.GiveawayEventHistory;
import christmas.model.menu.Menu;
import christmas.model.menu.Menus;
import christmas.model.money.Money;

public class IOManager {

    private static final String OUTPUT_END = "\n";

    private final InputView inputView;
    private final OutputView outputView;
    private final StringBuilder sb;

    public IOManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        sb = new StringBuilder();
    }

    public Day readArrivalDate() {
        outputView.printWelcomeMessage();
        boolean isInLoop = true;
        Day reservationDay = null;
        while (isInLoop) {
            try {
                isInLoop = false;
                outputView.printReadDateMessage();
                String userInput = inputView.readArrivalDate();
                int parsedInteger = Integer.parseInt(userInput);
                reservationDay = Day.of(parsedInteger);
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e);
                isInLoop = true;
            }
        }
        return reservationDay;
    }

    public Menus readMenus() {
        boolean isInLoop = true;
        Menus menus = null;
        while (isInLoop) {
            try {
                isInLoop = false;
                outputView.printReadMenuMessage();
                String userInput = inputView.readMenus();
                menus = Menus.fromString(userInput, INPUT_DELIMITER.getValue());
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e);
                isInLoop = true;
            }
        }

        return menus;
    }

    public void printAllMenus(Menus menus) {
        for (Menu menu : menus.getMenus().keySet()) {
            sb.append(menu)
                    .append(" ")
                    .append(menus.getMenus().get(menu))
                    .append(MENU_UNIT.getValue())
                    .append(OUTPUT_END);
        }
        outputView.printAllMenus(sb.toString());
        clearStringBuilder();
    }

    public void printTotalPriceBeforeDiscount(Money totalPrice) {
        outputView.printTotalPriceBeforeDiscount(convertIntegerToStringWithComma(totalPrice.getAmount()));
    }

    public void printGiveawayEvent(GiveawayEventHistory history) {
        if (history.getDetails().isEmpty()) {
            sb.append(NONE.getValue())
                    .append(OUTPUT_END);
            outputView.printGiveawayEvent(sb.toString());
            clearStringBuilder();
            return;
        }

        for (GiveawayEventConstant constant : history.getDetails().keySet()) {
            sb.append(constant.getEventMenu())
                    .append(" ")
                    .append(history.getDetails().get(constant))
                    .append(MENU_UNIT.getValue())
                    .append(OUTPUT_END);
        }
        outputView.printGiveawayEvent(sb.toString());
        clearStringBuilder();
    }

    public void printBenefitsDetail(DiscountEventHistory discountEventHistory,
                                    GiveawayEventHistory giveawayEventHistory) {
        if (discountEventHistory.getDetails().isEmpty() && giveawayEventHistory.getDetails().isEmpty()) {
            sb.append(NONE.getValue())
                    .append(OUTPUT_END);
            outputView.printBenefitsDetail(sb.toString());
            clearStringBuilder();
            return;
        }

        for (DiscountEventConstant constant : discountEventHistory.getDetails().keySet()) {
            sb.append(constant.getName())
                    .append(DISCOUNT_DELIMITER.getValue())
                    .append(" ")
                    .append(MINUS.getValue())
                    .append(convertIntegerToStringWithComma(discountEventHistory.getDetails().get(constant)))
                    .append(OUTPUT_END);
        }

        int amount = 0;
        for (GiveawayEventConstant constant : giveawayEventHistory.getDetails().keySet()) {
            amount += constant.getEventMenu().getPrice() * giveawayEventHistory.getDetails().get(constant);
        }
        sb.append(GIVEAWAY_EVENT.getMessage())
                .append(DISCOUNT_DELIMITER.getValue())
                .append(" ")
                .append(MINUS.getValue())
                .append(convertIntegerToStringWithComma(amount));
        outputView.printBenefitsDetail(sb.toString());
        clearStringBuilder();
    }

    public void printTotalBenefitsAmount(Money totalBenefitsAmount) {
        outputView.printTotalBenefitsAmount(
                MINUS.getValue() + convertIntegerToStringWithComma(totalBenefitsAmount.getAmount()));
    }

    public void printTotalPriceAfterDiscount(Money totalPriceAfterDiscount) {
        outputView.printTotalPriceAfterDiscount(convertIntegerToStringWithComma(totalPriceAfterDiscount.getAmount()));
    }

    public void printEventBadge(EventBadge eventBadge) {
        outputView.printEventBadge(eventBadge.getName());
    }

    private void clearStringBuilder() {
        sb.setLength(0);
    }
}
