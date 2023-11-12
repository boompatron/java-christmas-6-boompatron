package christmas.error;

public enum ErrorMessage {

    ERROR_PREFIX("[ERROR] "),
    INVALID_INPUT("제대로된 입력을 입력해주세요"),
    DIGIT_ONLY("숫자만 입력 해주세요"),
    DELIMITER_IN_START_OR_END("구분자는 시작, 끝에 올 수 없습니다."),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ONLY_DRINKS("음료만 포함하면 주문할 수 없습니다."),
    MENU_OVER_MAX_COUNT("메뉴는 최대 20개까지만 주문할 수 있습니다."),
    MONEY_ONLY_POSITIVE("금액은 0원 이상만 입력할 수 있습니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX.message + message;
    }
}
