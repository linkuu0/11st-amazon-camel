package org.link.camel.config.error.exception;

import org.link.camel.config.error.ErrorCode;

public class XmlDataBindException extends ServiceLogicException {

    public XmlDataBindException(ErrorCode errorCode) {
        super(errorCode);
    }
}
