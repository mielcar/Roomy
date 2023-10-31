package com.bu.roomy.domain

class Fixtures {

    static List<Customer> economyCustomers() {
        [
            new Customer(Price.withDefaultCurrency(23d)),
            new Customer(Price.withDefaultCurrency(45d)),
            new Customer(Price.withDefaultCurrency(22d)),
            new Customer(Price.withDefaultCurrency(99d))
        ]
    }

    static List<Customer> premiumCustomers() {
        [
            new Customer(Price.withDefaultCurrency(155d)),
            new Customer(Price.withDefaultCurrency(374d)),
            new Customer(Price.withDefaultCurrency(100d)),
            new Customer(Price.withDefaultCurrency(101d)),
            new Customer(Price.withDefaultCurrency(115d)),
            new Customer(Price.withDefaultCurrency(209d))
        ]
    }


    static LinkedList<Customer> createCustomers(int customersAmount) {
        LinkedList<Customer> customers = []
        for (int i = 0; i < customersAmount; i++)
            customers.add(new Customer(Price.withDefaultCurrency(69d)))
        customers
    }
}
