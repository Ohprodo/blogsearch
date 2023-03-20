package com.company.blogsearch.dto;

import com.company.blogsearch.constant.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CommonResponse<T> extends ErrorResponse {
    private final T data;

    private CommonResponse(T data) {
        super(ErrorCode.OK.getResultCode(), ErrorCode.OK.getResultMessage());
        this.data = data;
    }

    public static <T> CommonResponse<T> of(T data) {
        return new CommonResponse<>(data);
    }
}
