package com.bu.roomy.domain

import spock.lang.Specification

class UpgradableBookingsTest extends Specification {

    def 'Should properly count higher grade free rooms (getting into accounting overbooking)'() {
        given:
            UpgradableBookings economy = new UpgradableBookings(3, Fixtures.createCustomers(1))
            UpgradableBookings premium = new UpgradableBookings(3, Fixtures.createCustomers(4))
            UpgradableBookings ultraPremium = new UpgradableBookings(3, Fixtures.createCustomers(1))

            economy.setHigherGradeBookings(premium)
            premium.setHigherGradeBookings(ultraPremium)

        expect:
            economy.findThisAndHigherGradeFreeRooms() == 3
            economy.higherGradeFreeRooms() == 1
    }

    def 'Should properly upgrade different configurations of customer bookings'() {
        given:
            UpgradableBookings economy = new UpgradableBookings(3, Fixtures.createCustomers(7))
            UpgradableBookings premium = new UpgradableBookings(3, Fixtures.createCustomers(4))
            UpgradableBookings ultraPremium = new UpgradableBookings(3, Fixtures.createCustomers(1))

            economy.setHigherGradeBookings(premium)
            premium.setHigherGradeBookings(ultraPremium)

        when:
            economy.upgradeThisAndHigherGrade()

        then:
            economy.bookedRoomsAmount == 3
            premium.bookedRoomsAmount == 3
            ultraPremium.bookedRoomsAmount == 3
    }
}
