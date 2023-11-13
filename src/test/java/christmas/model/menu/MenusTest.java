package christmas.model.menu;

import static christmas.error.ErrorMessage.MENU_OVER_MAX_COUNT;
import static christmas.error.ErrorMessage.ONLY_DRINKS;
import static christmas.model.menu.Menu.제로콜라;
import static christmas.model.menu.Menu.타파스;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenusTest {

    private final String delimiter = ",";

    @DisplayName("문자열을 메뉴로 정상적으로 변환한다.")
    @Test
    void convertStringToMenusTest() {
        // given
        String rawMenus = "타파스-1,제로콜라-1";
        Map<Menu, Integer> expected = Map.of(타파스, 1, 제로콜라, 1);

        // when
        Menus menus = Menus.fromString(rawMenus, delimiter);

        // then
        assertThat(menus.getMenus())
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @DisplayName("주문한 메뉴의 총 가격을 정상적으로 반환받는다.")
    @Test
    void getTotalPriceTest() {
        // given
        String rawMenus = "타파스-1,제로콜라-1";
        Menus menus = Menus.fromString(rawMenus, delimiter);
        int expected = 타파스.getPrice() + 제로콜라.getPrice();

        // when
        int totalPrice = menus.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(expected);
    }

    @DisplayName("주문한 메뉴 중 특정 메뉴 타입의 개수를 반환받을 수 있다.")
    @Test
    void getCountByMenuTypeTest() {
        // given
        String rawMenus = "타파스-1,제로콜라-1";
        Menus menus = Menus.fromString(rawMenus, delimiter);
        int 타파스menuType = 1, 제로콜라menuType = 1;

        // when
        int 타파스count = menus.getCountByMenuType(타파스.getMenuType());
        int 제로콜라count = menus.getCountByMenuType(제로콜라.getMenuType());

        // then
        assertThat(타파스count).isEqualTo(타파스menuType);
        assertThat(제로콜라count).isEqualTo(제로콜라menuType);
    }

    @DisplayName("음료만 주문하면 예외를 발생시킨다.")
    @Test
    void onlyDrinksOccurExceptionTest() {
        // given
        String rawMenus = "제로콜라-1,샴페인-2";

        // when then
        assertThatThrownBy(() -> Menus.fromString(rawMenus, delimiter))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ONLY_DRINKS.getMessage());
    }

    @DisplayName("총 주문한 메뉴의 개수가 20개를 넘어가면 예외를 발생시킨다.")
    @Test
    void orderOverThreshHoldOccurExceptionTest() {
        // given
        String rawMenus = "티본스테이크-10,바비큐립-15,초코케이크-20,제로콜라-10";

        // when then
        assertThatThrownBy(() -> Menus.fromString(rawMenus, delimiter))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MENU_OVER_MAX_COUNT.getMessage());
    }
}