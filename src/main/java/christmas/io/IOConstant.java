package christmas.io;

public enum IOConstant {
    INPUT_DELIMITER(","),
    OUTPUT_DELIMITER(","),
    DISCOUNT_DELIMITER(":"),
    MENU_FORMAT_REGEX("[ㄱ-ㅎㅏ-ㅣ가-힣]+-[0-9]+"),
    MENU_UNIT("개"),
    NONE("없음"),
    MINUS("-"),
    ;

    private String value;

    IOConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
