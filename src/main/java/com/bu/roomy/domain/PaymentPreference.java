package com.bu.roomy.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Currency;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentPreference {

    private final static Currency DEFAULT_CURRENCY = Currency.getInstance("EUR");

    double value;

    Currency currency;

    static PaymentPreference withDefaultCurrency(double value) {
        if (value <= 0)
            throw new IllegalArgumentException("Payment value must be greater than 0! value=" + value);
        return new PaymentPreference(value, DEFAULT_CURRENCY);
    }
}
