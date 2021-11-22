package org.link.camel.config.error.exception;

import org.link.camel.config.error.ErrorCode;
import org.link.camel.config.error.ErrorResponse;

public class ProductInfoNotFoundException extends ServiceLogicException {

    public ProductInfoNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ProductInfoNotFoundException(ErrorCode errorCode, String response) {
        super(errorCode);
    }
}
