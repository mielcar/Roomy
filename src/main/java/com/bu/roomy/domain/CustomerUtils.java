package com.bu.roomy.domain;

import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
class CustomerUtils {

    double sumPrices(List<Customer> customers) {
        return customers.stream()
            .map(Customer::preferencedPrice)
            .map(Price::getPrice)
            .reduce(0d, Double::sum);
    }

    List<Customer> sortDesc(List<Customer> customers) {
        return customers.stream()
            .sorted(Comparator.comparingDouble(Customer::getPriceValue).reversed())
            .collect(Collectors.toList());
    }

    List<Customer> getHighest(List<Customer> customers, int maxAmount) {
        List<Customer> sortedDescCustomers = sortDesc(customers);
        return maxAmount >= sortedDescCustomers.size()
            ? sortedDescCustomers
            : sortedDescCustomers.subList(0, maxAmount);
    }
}
