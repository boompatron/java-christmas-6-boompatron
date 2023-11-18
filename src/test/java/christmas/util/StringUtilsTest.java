package christmas.util;

import static christmas.model.menu.Menu.제로콜라;
import static christmas.model.menu.Menu.타파스;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.menu.Menu;
import christmas.model.menu.Menus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @DisplayName("정수를 천단위로 끊고, 화폐 단위를 붙인 문자열로 변환할 수 있다.")
    @Test
    void convertIntegerToStringWithCommaTest() {
        // given
        int[] ints = {1, 1000, 25000, 1000000};
        String[] expected = {"1원", "1,000원", "25,000원", "1,000,000원"};
        List<String> expectedList = new ArrayList<>();

        // when
        for (int i = 0; i < ints.length; i++) {
            expectedList.add(StringUtils.convertIntegerToStringWithComma(ints[i]));
        }

        // then
        assertThat(expected)
                .usingRecursiveComparison()
                .isEqualTo(expectedList.toArray());
    }

    @DisplayName("문자열을 Menus 객체로 정상적으로 변환이 가능하다.")
    @Test
    void convertStringToMenusTest() {
        // given
        String rawMenus = "타파스-1,제로콜라-1";
        String delimiter = ",";
        Menus expected = Menus.fromString(rawMenus, delimiter);
        Map<Menu, Integer> expectedMenus = Map.of(타파스, 1, 제로콜라, 1);

        // when
        Map<Menu, Integer> menus = StringUtils.convertStringToMenus(rawMenus, delimiter);

        // then
        assertThat(menus)
                .usingRecursiveComparison()
                .isEqualTo(expected.getMenus());
    }
}
