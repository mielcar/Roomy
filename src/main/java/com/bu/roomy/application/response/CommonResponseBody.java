package com.bu.roomy.application.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class CommonResponseBody<T> {

    private final T result;

    private final List<String> errors;

    static <T> CommonResponseBody<T> from(T result) {
        return new CommonResponseBody<>(result, Collections.emptyList());
    }

    static <T> CommonResponseBody<T> from(List<String> errors) {
        return new CommonResponseBody<>(null, errors);
    }
}
