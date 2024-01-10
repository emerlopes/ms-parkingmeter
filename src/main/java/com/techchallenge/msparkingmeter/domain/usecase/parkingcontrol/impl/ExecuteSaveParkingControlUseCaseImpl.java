package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.application.exceptions.ParkingControlValidationException;
import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrol.IParkingControlDomainService;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteSaveParkingControlUseCase;
import com.techchallenge.msparkingmeter.infrastructure.msdrivers.IDriversClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ExecuteSaveParkingControlUseCaseImpl implements IExecuteSaveParkingControlUseCase {

    private final IParkingControlDomainService parkingControlDomainService;

    public ExecuteSaveParkingControlUseCaseImpl(IParkingControlDomainService parkingControlDomainService) {
        this.parkingControlDomainService = parkingControlDomainService;
    }

    @Override
    public CustomData<ParkingControlDomainEntityOutput> execute(ParkingControlDomainEntityInput input) {
        final var periodTypeId = input.getPeriodType().getParkingControlPeriodId();
        final var durationInMinutes = input.getDurationInMinutes();

        if (periodTypeId.equals(PeriodTypeEnum.FIXED.getValue())) {
            if (durationInMinutes == null || durationInMinutes == 0) {
                throw new ParkingControlValidationException("Duration in minutes is required when period type is fixed");
            }
        }

        if (Objects.equals(periodTypeId, PeriodTypeEnum.VARIABLE.getValue())) {
            input.setDurationInMinutes(0);
        }

        final var output = parkingControlDomainService.saveParkingControl(input);

        CustomData<ParkingControlDomainEntityOutput> customData = new CustomData<>();
        customData.setData(output);

        return customData;
    }

}
