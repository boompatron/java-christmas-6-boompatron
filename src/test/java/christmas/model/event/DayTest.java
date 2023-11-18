package christmas.model.event;

import static christmas.error.ErrorMessage.INVALID_DATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayTest {

    @DisplayName("Day 객체는 1~31 사이의 날짜만 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 20, 30, 31})
    void dayInRangeTest(int day) {
        // given
        int dayInRange = day;

        //when
        Day normalDay = Day.of(dayInRange);

        // then
        assertThat(normalDay.getDay()).isEqualTo(dayInRange);
    }

    @DisplayName("Day 객체는 1~31 이외의 날짜가 입력으로 들어오면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32, 33})
    void dayOutOfRangeTest(int day) {
        // given
        int dayOutRange = day;

        //when then
        assertThatThrownBy(() -> Day.of(dayOutRange))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DATE.getMessage());
    }
}