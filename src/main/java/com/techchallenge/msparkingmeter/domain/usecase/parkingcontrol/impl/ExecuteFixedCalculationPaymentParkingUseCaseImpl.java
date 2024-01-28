package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.ExecuteFixedCalculationPaymentParkingUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExecuteFixedCalculationPaymentParkingUseCaseImpl implements ExecuteFixedCalculationPaymentParkingUseCase {

    private static final BigDecimal FIXED_PARKING_PRICE = BigDecimal.valueOf(2.0);
    private static final BigDecimal VARIABLE_PARKING_PRICE = BigDecimal.valueOf(5.0);

    @Override
    public void execute(ParkingControlDomainEntityInput input) {

        final var durationInMinutes = input.getDurationInMinutes();
        final var hours = convertMinutesToHours(durationInMinutes);
        final var periodType = input.getPeriodType();

        if (PeriodTypeEnum.isFixed(periodType.getParkingControlPeriodId())) {

            final var predictedValueToBePaid = FIXED_PARKING_PRICE.multiply(BigDecimal.valueOf(hours));

            input.setPredictedValueToBePaid(predictedValueToBePaid);
        }

    }

    private Integer convertMinutesToHours(int durationInMinutes) {
        return (int) Math.ceil(durationInMinutes / 60.0);
    }
}
