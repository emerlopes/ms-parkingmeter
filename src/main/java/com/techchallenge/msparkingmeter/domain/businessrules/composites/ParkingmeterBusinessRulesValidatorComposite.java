package com.techchallenge.msparkingmeter.domain.businessrules.composites;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;

public interface ParkingmeterBusinessRulesValidatorComposite {
    boolean isValid(ParkingControlDomainEntityInput input);

    RuntimeException getException();
}
