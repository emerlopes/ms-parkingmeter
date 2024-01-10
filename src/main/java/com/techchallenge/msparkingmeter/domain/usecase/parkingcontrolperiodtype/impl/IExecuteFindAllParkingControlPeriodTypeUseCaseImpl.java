package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.impl;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrolperiodtype.IParkingControlPeriodTypeDomainService;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteFindAllParkingControlPeriodTypeUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IExecuteFindAllParkingControlPeriodTypeUseCaseImpl implements IExecuteFindAllParkingControlPeriodTypeUseCase {

    private final IParkingControlPeriodTypeDomainService iParkingControlPeriodTypeDomainService;

    public IExecuteFindAllParkingControlPeriodTypeUseCaseImpl(IParkingControlPeriodTypeDomainService iParkingControlPeriodTypeDomainService) {
        this.iParkingControlPeriodTypeDomainService = iParkingControlPeriodTypeDomainService;
    }

    @Override
    public CustomData<List<ParkingControlPeriodTypeDomainEntityOutput>> execute() {
        final var output = iParkingControlPeriodTypeDomainService.findAllParkingControlPeriodType();
        CustomData<List<ParkingControlPeriodTypeDomainEntityOutput>> customData = new CustomData<>();
        customData.setData(output);
        return customData;
    }
}
