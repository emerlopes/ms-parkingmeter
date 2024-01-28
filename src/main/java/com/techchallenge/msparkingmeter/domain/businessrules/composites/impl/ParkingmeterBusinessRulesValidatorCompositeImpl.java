package com.techchallenge.msparkingmeter.domain.businessrules.composites.impl;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.businessrules.composites.ParkingmeterBusinessRulesValidatorComposite;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.ExecuteCalculationFixedAmountExpectedToBePaidUseCaseImpl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ParkingmeterBusinessRulesValidatorCompositeImpl implements ParkingmeterBusinessRulesValidatorComposite {

    private final List<ParkingmeterBusinessRulesValidatorComposite> validators;

    private final ExecuteCalculationFixedAmountExpectedToBePaidUseCaseImpl executeCalculationFixedAmountExpectedToBePaidUseCaseImpl;

    private RuntimeException exception;


    public ParkingmeterBusinessRulesValidatorCompositeImpl(List<ParkingmeterBusinessRulesValidatorComposite> validators,
                                                           ExecuteCalculationFixedAmountExpectedToBePaidUseCaseImpl executeCalculationFixedAmountExpectedToBePaidUseCaseImpl) {
        this.validators = validators;
        this.executeCalculationFixedAmountExpectedToBePaidUseCaseImpl = executeCalculationFixedAmountExpectedToBePaidUseCaseImpl;
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
            input.setDurationInMinutes(0);
            input.setPredictedValueToBePaid(BigDecimal.ZERO);
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

        executeCalculationFixedAmountExpectedToBePaidUseCaseImpl.execute(input);

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
