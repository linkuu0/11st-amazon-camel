package org.link.camel.config.error.exception;

import lombok.Getter;
import org.link.camel.config.error.ErrorCode;

@Getter
public class ServiceLogicException extends RuntimeException {
    private final ErrorCode errorCode;

    public ServiceLogicException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
