package com.bu.roomy.domain

import spock.lang.Specification

class EconomyBookingsTest extends Specification {

    def 'Should create regular booking'() {
        given:
            List<Customer> economyCustomers = Fixtures.economyCustomers()
            List<Customer> premiumCustomers = Fixtures.premiumCustomers()
            PremiumBookings premiumBookings = PremiumBookings.create(premiumRooms, premiumCustomers)

        when:
            EconomyBookings result = EconomyBookings.create(economyRooms, economyCustomers, premiumBookings)

        then:
            result.totalValue == economyValue
            result.bookedRoomsAmount == economyBookedRooms
            premiumBookings.totalValue == premiumValue
            premiumBookings.bookedRoomsAmount == premiumBookedRooms

        where:
            premiumRooms | economyRooms | economyValue | economyBookedRooms | premiumValue | premiumBookedRooms
            3            | 2            | 144d         | 2                  | 738d         | 3
            6            | 2            | 144d         | 2                  | 1054d        | 6
            7            | 2            | 68d          | 2                  | 1153d        | 7
            8            | 2            | 45d          | 2                  | 1198d        | 8
            15           | 2            | 45d          | 2                  | 1198d        | 8
            15           | 4            | 189d         | 4                  | 1054d        | 6
            15           | 8            | 189d         | 4                  | 1054d        | 6
    }
}
