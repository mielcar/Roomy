package com.bu.roomy.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.With;

import java.util.List;

@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class PremiumBookings implements Bookings {

    private final int totalRoomsAmount;

    @With
    private final List<Customer> bookings;

    static PremiumBookings create(int totalRoomsAmount, List<Customer> premiumCustomers) {
        return new PremiumBookings(totalRoomsAmount, CustomerUtils.getHighest(premiumCustomers, totalRoomsAmount));
    }

    @Override
    public int getBookedRoomsAmount() {
        return bookings.size();
    }

    @Override
    public double getTotalValue() {
        return CustomerUtils.sumPrices(bookings);
    }

    int getFreeRoomsAmount() {
        return totalRoomsAmount - getBookedRoomsAmount();
    }

    void book(List<Customer> upgrades) {
        this.bookings.addAll(upgrades);
    }
}
