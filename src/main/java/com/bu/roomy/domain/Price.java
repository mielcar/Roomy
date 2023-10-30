package com.bu.roomy.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Currency;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Price {

    private final static Currency DEFAULT_CURRENCY = Currency.getInstance("EUR");

    double price;

    Currency currency;

    static Price withDefaultCurrency(double value) {
        if (value <= 0)
            throw new IllegalArgumentException("Price value must be greater than 0! value=" + value);
        return new Price(value, DEFAULT_CURRENCY);
    }
}
