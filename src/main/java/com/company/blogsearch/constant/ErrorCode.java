package com.company.blogsearch.constant;

import com.company.blogsearch.exception.GeneralException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK("0000", "SUCCESS", HttpStatus.OK),

    BAD_REQUEST("5000", "BAD REQUEST", HttpStatus.BAD_REQUEST),

    INTERNAL_ERROR("9000", "INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    KAKAO_HTTP_CONN_ERROR("9001", "KAKAO HTTP CONNECTION ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    NAVER_HTTP_CONN_ERROR("9002", "NAVER HTTP CONNECTION ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    DB_INSERT_ERROR("9003", "DB INSERT ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    DB_SELECT_ERROR("9004", "DB SELECT ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    DB_UPDATE_ERROR("9005", "DB UPDATE ERROR", HttpStatus.INTERNAL_SERVER_ERROR);



    private final String resultCode;
    private final String resultMessage;
    private final HttpStatus status;

    public static ErrorCode valueOf(HttpStatus httpStatus) {
        if (httpStatus == null) { throw new GeneralException("HttpStatus is null."); }

        return Arrays.stream(values())
                .filter(errorCode -> errorCode.getStatus() == httpStatus)
                .findFirst()
                .orElseGet(() -> {
                    if (httpStatus.is4xxClientError()) { return ErrorCode.BAD_REQUEST; }
                    else if (httpStatus.is5xxServerError()) { return ErrorCode.INTERNAL_ERROR; }
                    else { return ErrorCode.OK; }
                });
    }

    public String getResultMessage(Throwable e) {
        return this.getResultMessage(this.getResultMessage() + " - " + e.getMessage());
    }

    public String getResultMessage(String message) {
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(this.getResultMessage());
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", this.name(), this.getResultCode());
    }
}
