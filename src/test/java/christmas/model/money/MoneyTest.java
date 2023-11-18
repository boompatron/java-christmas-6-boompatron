package christmas.model.money;

import static christmas.error.ErrorMessage.MONEY_ONLY_POSITIVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("금액이 0 또는 양수일 경우 정상적으로 객체를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 1000})
    void createMoneyTest(int zeroOrPositive) {
        // given
        int amount = zeroOrPositive;

        // when
        Money money = Money.of(amount);

        // then
        assertThat(money.getAmount()).isEqualTo(amount);
    }

    @DisplayName("금액이 음수일 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-100, -200})
    void createMoneyWithNegativeOrZeroAmountTest(int negative) {
        // given
        int amount = negative;

        // when then
        assertThatThrownBy(() -> Money.of(amount))
            .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_ONLY_POSITIVE.getMessage());
    }

    @DisplayName("금액은 양수가 들어온 경우 증액될 수 있다.")
    @Test
    void addAmountTest() {
        // given
        int originAmount = 1000;
        int addAmount = 1000;
        Money money = Money.of(originAmount);

        // when
        money.addAmount(addAmount);

        // then
        assertThat(money.getAmount()).isEqualTo(originAmount + addAmount);
    }
}