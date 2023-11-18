package christmas.util;

import christmas.model.menu.Menu;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {

    private static final DecimalFormat THOUSANDS_FORMAT = new DecimalFormat("###,###");
    private static final String ZERO = "0";
    private static final String MINUS = "-";
    private static final String CURRENCY_UNIT = "원";

    private StringUtils() {
    }

    public static String convertIntegerToStringWithComma(int integer) {
        if (integer == 0) {
            return ZERO + CURRENCY_UNIT;
        }
        return THOUSANDS_FORMAT.format(integer) + CURRENCY_UNIT;
    }

    public static Map<Menu, Integer> convertStringToMenus(String menuInput, String delimiter) {
        Map<Menu, Integer> menus = new HashMap<>();
        String[] splitMenus = menuInput.split(delimiter);
        for (String menu : splitMenus) {
            String[] menuNum = menu.split("-");
            Menu menuName = parseMenuFromString(menuNum[0]);
            int menuCount = Integer.parseInt(menuNum[1]);
            int beforeCount = menus.getOrDefault(menuName, 0);

            menus.put(menuName, beforeCount + menuCount);
        }

        return menus;
    }

    private static Menu parseMenuFromString(String rawMenu) {
        return Menu.valueOf(rawMenu);
    }
}
