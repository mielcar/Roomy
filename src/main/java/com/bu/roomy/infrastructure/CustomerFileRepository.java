package com.bu.roomy.infrastructure;

import com.bu.roomy.domain.Customer;
import com.bu.roomy.domain.CustomerRepository;
import com.bu.roomy.domain.Price;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Primary
@Repository
class CustomerFileRepository implements CustomerRepository {

    @Value("classpath:${customer.preferences.file.path}")
    private Resource customerPreferences;

    @Getter
    private List<Customer> customers;

    @PostConstruct
    void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Double> preferredPrices = objectMapper.readValue(customerPreferences.getFile(), new TypeReference<>() {
        });

        preferredPrices.forEach(preferredPrice -> {
            if (preferredPrice < 0)
                throw new BeanInitializationException("Can't create CustomerFileRepository -> one of preferred prices is lower than 0");
        });

        this.customers = preferredPrices.stream()
            .map(Price::withDefaultCurrency)
            .map(Customer::new)
            .toList();
    }
}
