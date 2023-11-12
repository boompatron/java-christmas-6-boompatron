package christmas.model.event;

import java.util.HashMap;
import java.util.Map;

public class DiscountEventHistory {

    private final Map<DiscountEventConstant, Integer> details;

    public DiscountEventHistory() {
        this.details = new HashMap<>();
    }

    public void addDiscountEvent(DiscountEventConstant DiscountEventConstant, int amount) {
        details.put(DiscountEventConstant, amount);
    }

    public Map<DiscountEventConstant, Integer> getDetails() {
        return details;
    }
}
