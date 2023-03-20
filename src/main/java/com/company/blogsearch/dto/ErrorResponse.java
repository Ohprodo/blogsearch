package com.company.blogsearch.dto;

import com.company.blogsearch.constant.ErrorCode;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private final String resultCode;
    private final String resultMessage;

    public static ErrorResponse of(String resultCode, String resultMessage) {
        return new ErrorResponse(resultCode, resultMessage);
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getResultCode(), errorCode.getResultMessage());
    }

}
