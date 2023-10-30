package com.bu.roomy.domain

import spock.lang.Specification

class PremiumBookingsTest extends Specification {

    def 'Should properly pick best paying premium customers during booking creation'() {
        given:
            List<Customer> premiumCustomers = Fixtures.premiumCustomers()

        when:
            PremiumBookings result = PremiumBookings.create(totalRoomsAmount, premiumCustomers)

        then:
            result.bookedRoomsAmount == bookedRoomsAmount
            result.freeRoomsAmount == freeRoomsAmount
            result.totalValue == totalValue

        where:
            totalRoomsAmount | bookedRoomsAmount | freeRoomsAmount | totalValue
            3                | 3                 | 0               | 738d
            6                | 6                 | 0               | 1054d
            7                | 6                 | 1               | 1054d
    }

    def 'Should properly book additional rooms'() {
        given:
            List<Customer> premiumCustomers = Fixtures.premiumCustomers()
            PremiumBookings premiumBookings = PremiumBookings.create(8, premiumCustomers)
            assert premiumBookings.freeRoomsAmount == 2
            assert premiumBookings.totalValue == 1054d

        when:
            premiumBookings.book([
                new Customer(Price.withDefaultCurrency(69d)),
                new Customer(Price.withDefaultCurrency(100d))
            ])

        then:
            premiumBookings.totalValue == 1054d + 69d + 100d
            premiumBookings.freeRoomsAmount == 0
    }
}
