package christmas;

import christmas.io.IOManager;
import christmas.io.InputView;
import christmas.io.OutputView;
import christmas.controller.Controller;
import christmas.service.DiscountEventService;
import christmas.service.GiveawayEventService;

public class Application {
    public static void main(String[] args) {
        IOManager ioManager = new IOManager(new InputView(), new OutputView());
        Controller controller = new Controller(ioManager, DiscountEventService.of(), GiveawayEventService.of());
        controller.run();
    }
}
