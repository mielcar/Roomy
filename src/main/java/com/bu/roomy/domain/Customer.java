package com.bu.roomy.domain;

public record Customer(Price preferencedPrice) {

    double getPreferredPriceValue() {
        return preferencedPrice.getPrice();
    }
}
