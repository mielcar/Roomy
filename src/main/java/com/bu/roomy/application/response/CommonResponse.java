package com.bu.roomy.application.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CommonResponse<T> extends ResponseEntity<CommonResponseBody<T>> {

    private CommonResponse(T result) {
        super(CommonResponseBody.from(result), HttpStatus.OK);
    }

    private CommonResponse(List<String> errors) {
        super(CommonResponseBody.from(errors), HttpStatus.BAD_REQUEST);
    }

    public static <T> CommonResponse<T> withResult(T result) {
        return new CommonResponse<>(result);
    }

    public static <T> CommonResponse<T> withErrors(List<String> errors) {
        return new CommonResponse<>(errors);
    }
}
