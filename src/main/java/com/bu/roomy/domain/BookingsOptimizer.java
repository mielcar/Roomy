package com.bu.roomy.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingsOptimizer {

    private final CustomerRepository customerRepository;

    public OptimizedBookings optimize(RoomsDetails roomsDetails) {
        List<Customer> allCustomers = customerRepository.getCustomers();
        List<Customer> economyCustomers = allCustomers.stream()
            .filter(customer -> customer.getPreferredPriceValue() < roomsDetails.priceThreshold())
            .toList();
        List<Customer> premiumCustomers = allCustomers.stream()
            .filter(customer -> customer.getPreferredPriceValue() >= roomsDetails.priceThreshold())
            .toList();

        UpgradableBookings economyBookings = new UpgradableBookings(roomsDetails.economyRoomsAmount(), economyCustomers);
        UpgradableBookings premiumBookings = new UpgradableBookings(roomsDetails.premiumRoomsAmount(), premiumCustomers);
        economyBookings.setHigherGradeBookings(premiumBookings);
        economyBookings.upgradeThisAndHigherGrade();

        return new OptimizedBookings(economyBookings, premiumBookings);
    }
}
