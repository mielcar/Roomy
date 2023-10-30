package com.bu.roomy.domain

import com.bu.roomy.infrastructure.CustomerInMemoryRepository
import spock.lang.Specification

class BookingsOptimizerTest extends Specification {

    BookingsOptimizer bookingsOptimizer = new BookingsOptimizer(new CustomerInMemoryRepository())

    def 'Should properly optimize bookings'() {
        given:
            RoomsDetails roomsDetails = new RoomsDetails(economyRoomsAvailable, premiumRoomsAvailable, 100d)

        when:
            OptimizedBookings result = bookingsOptimizer.optimize(roomsDetails)

        then:
            result.economy().totalValue == economyValue
            result.economy().bookedRoomsAmount == economyBookedRooms
            result.premium().totalValue == premiumValue
            result.premium().bookedRoomsAmount == premiumBookedRooms

        where:
            economyRoomsAvailable | premiumRoomsAvailable | economyValue | economyBookedRooms | premiumValue | premiumBookedRooms
            3                     | 3                     | 167d         | 3                  | 738d         | 3
            5                     | 7                     | 189d         | 4                  | 1054d        | 6
            7                     | 2                     | 189d         | 4                  | 583d         | 2
            1                     | 10                    | 22d          | 1                  | 1221d        | 9
    }
}
