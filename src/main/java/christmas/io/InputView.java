package christmas.io;

import static christmas.error.ErrorMessage.DELIMITER_IN_START_OR_END;
import static christmas.error.ErrorMessage.DIGIT_ONLY;
import static christmas.error.ErrorMessage.INVALID_INPUT;
import static christmas.error.ErrorMessage.INVALID_ORDER;
import static christmas.io.IOConstant.INPUT_DELIMITER;

import camp.nextstep.edu.missionutils.Console;
import java.util.Objects;

public class InputView {

    public InputView() {
    }

    public String readArrivalDate() {
        String userInput = Console.readLine();
        validateValidString(userInput);
        validateOnlyContainsDigit(userInput);
        return userInput;
    }

    public String readMenus() {
        String userInput = Console.readLine();
        validateValidString(userInput);
        validateIsDelimiterInStartOrEnd(userInput);
        validateValidMenuFormat(userInput);
        return userInput;
    }

    private void validateValidString(String input) {
        if (Objects.isNull(input) || input.isEmpty() || input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    private void validateOnlyContainsDigit(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(DIGIT_ONLY.getMessage());
        }
    }

    private void validateIsDelimiterInStartOrEnd(String input) {
        if (input.charAt(0) == INPUT_DELIMITER.getValue().charAt(0)
                || input.charAt(input.length() - 1) == INPUT_DELIMITER.getValue().charAt(0)) {
            throw new IllegalArgumentException(DELIMITER_IN_START_OR_END.getMessage());
        }
    }

    public void validateValidMenuFormat(String input) {
        String[] split = input.split(INPUT_DELIMITER.getValue());
        for (String check: split) {
            if (!check.matches(IOConstant.MENU_FORMAT_REGEX.getValue())) {
                throw new IllegalArgumentException(INVALID_ORDER.getMessage());
            }
        }
    }
}
