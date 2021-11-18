package org.link.camel.config.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private final LocalDateTime datetime = LocalDateTime.now();

    private int status;
    private String message;

    public static ErrorResponse create() {
        return new ErrorResponse();
    }

    public static ErrorResponse of(ErrorCode error) {
        ErrorResponse response = ErrorResponse.create();
        response.status = error.getStatus();
        response.message = error.getMessage();

        return response;
    }
}
