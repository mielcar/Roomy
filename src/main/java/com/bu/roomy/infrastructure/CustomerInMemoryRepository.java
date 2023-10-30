package com.bu.roomy.infrastructure;

import com.bu.roomy.domain.Customer;
import com.bu.roomy.domain.CustomerRepository;
import com.bu.roomy.domain.Price;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
class CustomerInMemoryRepository implements CustomerRepository {

    private final static List<Double> PREFERRED_PRICES = Stream.of(
        23d,
        45d,
        155d,
        374d,
        22d,
        99d,
        100d,
        101d,
        115d,
        209d
    ).toList();

    @Override
    public List<Customer> getCustomers() {
        return PREFERRED_PRICES.stream()
            .map(Price::withDefaultCurrency)
            .map(Customer::new)
            .toList();
    }
}
