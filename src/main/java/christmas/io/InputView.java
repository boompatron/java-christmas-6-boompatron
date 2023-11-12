package christmas.io;

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
            throw new IllegalArgumentException();
        }
    }

    private void validateOnlyContainsDigit(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateIsDelimiterInStartOrEnd(String input) {
        if (input.charAt(0) == INPUT_DELIMITER.getValue().charAt(0)
                || input.charAt(input.length() - 1) == INPUT_DELIMITER.getValue().charAt(0)) {
            throw new IllegalArgumentException();
        }
    }

    public void validateValidMenuFormat(String input) {
        String[] split = input.split(INPUT_DELIMITER.getValue());
        for (String check: split) {
            if (!check.matches(IOConstant.MENU_FORMAT_REGEX.getValue())) {
                throw new IllegalArgumentException();
            }
        }
    }
}
