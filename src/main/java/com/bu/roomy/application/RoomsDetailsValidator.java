package com.bu.roomy.application;

import com.bu.roomy.domain.RoomsDetails;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
class RoomsDetailsValidator {

    List<String> validate(RoomsDetails roomsDetails) {
        List<String> errors = new ArrayList<>();
        if (roomsDetails.economyRoomsAmount() < 0)
            errors.add("Economy rooms amount can't be lower than 0!");
        if (roomsDetails.premiumRoomsAmount() < 0)
            errors.add("Premium rooms amount can't be lower than 0!");
        if (roomsDetails.priceThreshold() < 0)
            errors.add("Price threshold can't be lower than 0!");
        return errors;
    }
}
