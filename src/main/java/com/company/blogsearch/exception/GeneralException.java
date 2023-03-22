package com.company.blogsearch.exception;

import com.company.blogsearch.constant.ErrorCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GeneralException extends RuntimeException{
    private final ErrorCode errorCode;

    public GeneralException() {
        super();
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }

    public GeneralException(String message) {
        super(message);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }

    public GeneralException(ErrorCode errorCode) {
        super(errorCode.getResultMessage());
        this.errorCode = errorCode;
    }

    public GeneralException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }

    public GeneralException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getResultMessage(), cause);
        this.errorCode = errorCode;
    }
}
