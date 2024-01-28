package com.techchallenge.msparkingmeter.domain.businessrules.leafs;

import com.techchallenge.msparkingmeter.application.exceptions.ParkingControlValidationException;
import com.techchallenge.msparkingmeter.domain.businessrules.composites.ParkingmeterBusinessRulesValidatorComposite;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import org.springframework.stereotype.Component;

@Component
public class DurationMinutesRequiredValidatorLeaf implements ParkingmeterBusinessRulesValidatorComposite {

    private ParkingControlValidationException parkingControlValidationException;

    @Override
    public boolean isValid(ParkingControlDomainEntityInput input) {

        final var durationInMinutes = input.getDurationInMinutes();

        if (durationInMinutes == null || durationInMinutes == 0) {
            parkingControlValidationException = new ParkingControlValidationException("Duration in minutes is required when period type is fixed");
            return false;
        }

        return true;
    }

    @Override
    public RuntimeException getException() {
        return parkingControlValidationException;
    }
}
