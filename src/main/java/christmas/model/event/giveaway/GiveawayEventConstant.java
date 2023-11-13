package christmas.model.event.giveaway;

import static christmas.model.menu.Menu.샴페인;

import christmas.model.menu.Menu;

public enum GiveawayEventConstant {

    GIVEAWAY_EVENT("증정 이벤트", 120000, 샴페인),;

    private final String name;
    private final int threshold;
    private final Menu eventMenu;

    GiveawayEventConstant(String name, int threshold, Menu eventMenu) {
        this.name = name;
        this.threshold = threshold;
        this.eventMenu = eventMenu;
    }

    public int getThreshold() {
        return threshold;
    }

    public String getName() {
        return name;
    }

    public Menu getEventMenu() {
        return eventMenu;
    }
}
