package com.bu.roomy.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class EconomyBookings implements Bookings {

    private final List<Customer> regularBookings;

    static EconomyBookings create(int totalRoomsAmount, List<Customer> economyCustomers, PremiumBookings premiumBookings) {
        List<Customer> regularBookings = new ArrayList<>();
        int freePremiumRoomsAmount = premiumBookings.getFreeRoomsAmount();
        if (isEconomyOverbookedAndFreePremiumRooms(totalRoomsAmount, economyCustomers, freePremiumRoomsAmount)) {
            List<Customer> economyCustomersSortedDesc = CustomerUtils.sortDesc(economyCustomers);
            int customersForUpgradeAmount = Math.min(freePremiumRoomsAmount, economyCustomersSortedDesc.size() - totalRoomsAmount);
            List<Customer> upgradedBookings = economyCustomersSortedDesc.subList(0, customersForUpgradeAmount);
            premiumBookings.book(upgradedBookings);
            regularBookings.addAll(economyCustomersSortedDesc.subList(customersForUpgradeAmount, customersForUpgradeAmount + totalRoomsAmount));
        } else {
            regularBookings.addAll(CustomerUtils.getHighest(economyCustomers, totalRoomsAmount));
        }
        return new EconomyBookings(regularBookings);
    }

    @Override
    public int getBookedRoomsAmount() {
        return regularBookings.size();
    }

    @Override
    public double getTotalValue() {
        return CustomerUtils.sumPrices(regularBookings);
    }

    private static boolean isEconomyOverbookedAndFreePremiumRooms(int totalRoomsAmount, List<Customer> economyCustomers, int freePremiumRoomsAmount) {
        return economyCustomers.size() > totalRoomsAmount && freePremiumRoomsAmount > 0;
    }
}
