package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.impl;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrolperiodtype.IParkingControlPeriodTypeDomainService;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteSaveParkingControlPeriodTypeUseCase;
import org.springframework.stereotype.Service;

@Service
public class ExecuteSaveParkingControlPeriodTypeUseCaseImpl implements IExecuteSaveParkingControlPeriodTypeUseCase {

    private final IParkingControlPeriodTypeDomainService iParkingControlPeriodTypeDomainService;

    public ExecuteSaveParkingControlPeriodTypeUseCaseImpl(IParkingControlPeriodTypeDomainService iParkingControlPeriodTypeDomainService) {
        this.iParkingControlPeriodTypeDomainService = iParkingControlPeriodTypeDomainService;
    }

    @Override
    public CustomData<ParkingControlPeriodTypeDomainEntityOutput> execute(ParkingControlPeriodTypeDomainEntityInput input) {
        final var output = iParkingControlPeriodTypeDomainService.saveParkingControlPeriodType(input);
        CustomData<ParkingControlPeriodTypeDomainEntityOutput> customData = new CustomData<>();
        customData.setData(output);

        return customData;
    }
}
