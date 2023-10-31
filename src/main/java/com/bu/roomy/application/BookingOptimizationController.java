package com.bu.roomy.application;

import com.bu.roomy.domain.BookingsOptimizer;
import com.bu.roomy.domain.OptimizedBookings;
import com.bu.roomy.domain.RoomsDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/bookings")
@RestController
@RequiredArgsConstructor
public class BookingOptimizationController {

    private final BookingsOptimizer bookingsOptimizer;

    @GetMapping(path = "/optimization")
    OptimizedBookings optimize(@RequestParam int economyRooms,
                               @RequestParam int premiumRooms,
                               @RequestParam double premiumThreshold) {
        RoomsDetails roomsDetails = new RoomsDetails(economyRooms, premiumRooms, premiumThreshold);
        return bookingsOptimizer.optimize(roomsDetails);
    }

}
