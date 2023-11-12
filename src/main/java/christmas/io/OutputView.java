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

    public void printShowEventMessage() {
        System.out.println(IOConstant.SHOW_EVENT_MESSAGE.getValue());
    }
}
