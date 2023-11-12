package christmas.model.event;

import java.util.HashMap;
import java.util.Map;

public class GiveawayEventHistory {

    private Map<GiveawayEventConstant, Integer> details;

    public GiveawayEventHistory() {
        this.details = new HashMap<>();
    }

    public void addGiveawayEvent(GiveawayEventConstant giveawayEventConstant, int amount) {
        details.put(giveawayEventConstant, amount);
    }

    public Map<GiveawayEventConstant, Integer> getDetails() {
        return details;
    }
}
