package christmas.model.menu;

import static christmas.util.StringUtils.convertStringToMenus;

import java.util.Map;

public class Menus {
    private static final int MENU_MAX_COUNT = 20;
    private static final int DEFAULT_MENU_COUNT = 0;

    private final Map<Menu, Integer> menus;

    private Menus(Map<Menu, Integer> menus) {
        this.menus = menus;
    }

    public static Menus fromString(String rawMenus, String delimiter) {
        Map<Menu, Integer> menus = convertStringToMenus(rawMenus, delimiter);
        validateMenusTotalOverThreshold(menus);
        validateMenuOnlyContainsDrinks(menus);
        return new Menus(menus);
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Menu menu : menus.keySet()) {
            totalPrice += menu.getPrice() * menus.get(menu);
        }

        return totalPrice;
    }

    public int getCountByMenuType(MenuType menuType) {
        int totalCount = 0;
        for (Menu menu: this.menus.keySet()) {
            if (menu.getMenuType() == menuType) {
                totalCount += this.menus.get(menu);
            }
        }

        return totalCount;
    }

    public Map<Menu, Integer> getMenus() {
        return menus;
    }

    private static void validateMenusTotalOverThreshold(Map<Menu, Integer> menus) {
        int totalCount = 0;
        for (Menu menu : menus.keySet()) {
            totalCount += menus.get(menu);
        }

        if (totalCount > MENU_MAX_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateMenuOnlyContainsDrinks(Map<Menu, Integer> menus) {
        if (menus.keySet().stream()
                .allMatch(Menu::isDrink)) {
            throw new IllegalArgumentException();
        }
    }
}
