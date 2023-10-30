package com.bu.roomy.domain;

public record Customer(Price preferencedPrice) {

    double getPriceValue() {
        return preferencedPrice.getPrice();
    }
}
