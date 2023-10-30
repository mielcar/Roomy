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
            .filter(customer -> customer.getPriceValue() < roomsDetails.priceThreshold())
            .toList();
        List<Customer> premiumCustomers = allCustomers.stream()
            .filter(customer -> customer.getPriceValue() >= roomsDetails.priceThreshold())
            .toList();
        PremiumBookings premiumBookings = PremiumBookings.create(roomsDetails.premiumRoomsAmount(), premiumCustomers);
        EconomyBookings economyBookings = EconomyBookings.create(roomsDetails.economyRoomsAmount(), economyCustomers, premiumBookings);
        return new OptimizedBookings(economyBookings, premiumBookings);
    }
}
