package christmas.io;

public enum IOConstant {
    INPUT_DELIMITER(","),
    OUTPUT_DELIMITER(","),
    DISCOUNT_DELIMITER(":"),
    WELCOME_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    READ_DATE_MESSAGE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    READ_MENU_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    SHOW_EVENT_MESSAGE("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),

    ORDERED_MENU("<주문 메뉴>"),
    AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIVEAWAY_MENU("\n<증정 메뉴>"),
    BENEFITS_DETAIL("<혜택 내역>"),
    TOTAL_BENEFITS_AMOUNT("\n<총혜택 금액>"),
    AMOUNT_AFTER_DISCOUNT("\n<할인 후 예상 결제 금액>"),
    EVENT_BADGE("\n<12월 이벤트 배지>"),
    MENU_FORMAT_REGEX("[ㄱ-ㅎㅏ-ㅣ가-힣]+-[0-9]+"),
    MENU_UNIT("개"),
    NONE("없음"),
    MINUS("-"),
    GIVEAWAY_EVENT("증정 이벤트"),
    ;

    private String value;

    IOConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
