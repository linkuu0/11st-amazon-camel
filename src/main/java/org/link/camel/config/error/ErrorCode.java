package org.link.camel.config.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 공통 에러 메시지
    INTERNAL_SERVER_ERROR(500, "내부 서버 에러"),
    BAD_REQUEST(400, "Bad Request"),
    METHOD_NOT_ALLOWED(400, "허용되지 않은 요청입니다."),
    INVALID_INPUT_VALUE(400, "잘못된 값이 입력되었습니다. 요청 데이터를 확인해주세요."),

    // 상품 정보 부분
    ITEM_NOT_FOUND(500, "해당 상품이 없습니다."),

    // API 부분
    API_PRODUCT_INFO_NOT_FOUND(500, "[API] 상품 정보 조회 실패"),
    API_XML_BIND_ERROR(500, "XML 데이터 바인딩 에러");

    private final int status;
    private final String message;

}

