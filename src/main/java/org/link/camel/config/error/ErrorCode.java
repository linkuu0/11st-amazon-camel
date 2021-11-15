package org.link.camel.config.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    ERROR(400, "123", "에러!");

    private final int status;
    private final String code;
    private final String message;

}

