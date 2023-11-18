package christmas.model.menu;

import static christmas.model.menu.MenuType.APPETIZER;
import static christmas.model.menu.MenuType.DESSERT;
import static christmas.model.menu.MenuType.DRINK;
import static christmas.model.menu.MenuType.MAIN;

public enum Menu {
    양송이수프(APPETIZER, 6000),
    타파스(APPETIZER, 5500),
    시저샐러드(APPETIZER, 8000),

    티본스테이크(MAIN, 55000),
    바비큐립(MAIN, 54000),
    해산물파스타(MAIN, 35000),
    크리스마스파트타(MAIN, 25000),

    초코케이크(DESSERT, 15000),
    아이스크림(DESSERT, 5000),

    제로콜라(DRINK, 3000),
    레드와인(DRINK, 60000),
    샴페인(DRINK, 25000);

    private final MenuType menuType;
    private final int price;

    Menu(MenuType menuType, int price) {
        this.menuType = menuType;
        this.price = price;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public int getPrice() {
        return price;
    }

    public boolean isDrink() {
        return this.menuType == DRINK;
    }

    public boolean isMain() {
        return this.menuType == MAIN;
    }

    public boolean isDesert() {
        return this.menuType == DESSERT;
    }
}
