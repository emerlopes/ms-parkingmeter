package com.techchallenge.msparkingmeter.domain.businessrules.composites.impl;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.businessrules.composites.ParkingmeterBusinessRulesValidatorComposite;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingmeterBusinessRulesValidatorCompositeImpl implements ParkingmeterBusinessRulesValidatorComposite {

    private final List<ParkingmeterBusinessRulesValidatorComposite> validators;

    private RuntimeException exception;


    public ParkingmeterBusinessRulesValidatorCompositeImpl(List<ParkingmeterBusinessRulesValidatorComposite> validators) {
        this.validators = validators;
    }

    public void addValidator(ParkingmeterBusinessRulesValidatorComposite validator) {
        this.validators.add(validator);
    }


    @Override
    public boolean isValid(ParkingControlDomainEntityInput input) {

        final var periodTypeId = input.getPeriodType().getParkingControlPeriodId();
        final var isFixedPeriodType = this.isFixedPeriodType(PeriodTypeEnum.valueOf(periodTypeId));

        if (!isFixedPeriodType) {
            System.out.println("Period type is not fixed");
            return true;
        }

        try {
            for (ParkingmeterBusinessRulesValidatorComposite validator : validators) {
                if (!validator.isValid(input)) {
                    this.exception = validator.getException();
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public RuntimeException getException() {
        return this.exception;
    }

    private boolean isFixedPeriodType(PeriodTypeEnum periodType) {
        return periodType.equals(PeriodTypeEnum.FIXED);
    }
}
