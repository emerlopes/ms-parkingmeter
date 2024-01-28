package com.techchallenge.msparkingmeter.domain.businessrules.leafs;

import com.techchallenge.msparkingmeter.application.exceptions.ParkingControlValidationException;
import com.techchallenge.msparkingmeter.domain.businessrules.composites.ParkingmeterBusinessRulesValidatorComposite;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import org.springframework.stereotype.Component;

@Component
public class MinimumMinutesDurationValidatorLeaf implements ParkingmeterBusinessRulesValidatorComposite {

    private ParkingControlValidationException parkingControlValidationException;

    private final static int MINIMUM_DURATION_IN_MINUTES = 60;

    @Override
    public boolean isValid(ParkingControlDomainEntityInput input) {

        final var durationInMinutes = input.getDurationInMinutes();

        if (durationInMinutes < MINIMUM_DURATION_IN_MINUTES) {
            parkingControlValidationException = new ParkingControlValidationException("The minimum duration for a fixed parking period is 60 minutes");
            return false;
        }

        return true;
    }

    @Override
    public RuntimeException getException() {
        return parkingControlValidationException;
    }
}
