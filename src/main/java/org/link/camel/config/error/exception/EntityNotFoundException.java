package org.link.camel.config.error.exception;

import org.link.camel.config.error.ErrorCode;

/**
 *
 */
public class EntityNotFoundException extends ServiceLogicException {
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
