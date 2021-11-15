package org.link.camel.config.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    ERROR(400, "에러!"),
    NOT_FOUND_PRODUCT(400, "해당 상품이 없습니다.");

    private final int status;
    private final String message;

}

