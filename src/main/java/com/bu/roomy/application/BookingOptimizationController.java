package com.bu.roomy.application;

import com.bu.roomy.application.response.CommonResponse;
import com.bu.roomy.domain.BookingsOptimizer;
import com.bu.roomy.domain.OptimizedBookings;
import com.bu.roomy.domain.RoomsDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Bookings", description = "Bookings statistics API")
@RequestMapping(path = "/bookings")
@RestController
@RequiredArgsConstructor
class BookingOptimizationController {

    private final BookingsOptimizer bookingsOptimizer;

    @Operation(summary = "Calculate optimized rooms occupancy")
    @GetMapping(path = "/optimization")
    CommonResponse<OptimizedBookings> optimize(@RequestParam @Min(value = 0, message = "Economy rooms amount can't be lower than 0") int economyRooms,
                                               @RequestParam @Min(value = 0, message = "Premium rooms amount can't be lower than 0") int premiumRooms,
                                               @RequestParam @Min(value = 0, message = "Price threshold can't be lower than 0") double premiumThreshold) {
        RoomsDetails roomsDetails = new RoomsDetails(economyRooms, premiumRooms, premiumThreshold);
        List<String> validationErrors = RoomsDetailsValidator.validate(roomsDetails);
        if (!validationErrors.isEmpty())
            return CommonResponse.withErrors(validationErrors);
        return CommonResponse.withResult(bookingsOptimizer.optimize(roomsDetails));
    }

}
