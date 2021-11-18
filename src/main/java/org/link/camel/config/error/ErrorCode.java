package org.link.camel.config.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 공통 에러 메시지
    ERROR(400, "에러!"),
    NOT_FOUND_PRODUCT(400, "해당 상품이 없습니다."),
    NOT_FOUND_PRODUCT_INFO_API(400, "[API] 상품 정보 연결 실패"),
    INTERNAL_SERVER_ERROR(500, "내부 서버 에러");

    private final int status;
    private final String message;

}

