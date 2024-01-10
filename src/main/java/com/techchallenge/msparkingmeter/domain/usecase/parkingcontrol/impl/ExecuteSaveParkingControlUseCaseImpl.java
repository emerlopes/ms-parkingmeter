package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrol.IParkingControlDomainService;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteSaveParkingControlUseCase;
import org.springframework.stereotype.Service;

@Service
public class ExecuteSaveParkingControlUseCaseImpl implements IExecuteSaveParkingControlUseCase {

    private final IParkingControlDomainService parkingControlDomainService;

    public ExecuteSaveParkingControlUseCaseImpl(IParkingControlDomainService parkingControlDomainService) {
        this.parkingControlDomainService = parkingControlDomainService;
    }

    @Override
    public CustomData<ParkingControlDomainEntityOutput> execute(ParkingControlDomainEntityInput input) {
        final var output = parkingControlDomainService.saveParkingControl(input);
        CustomData<ParkingControlDomainEntityOutput> customData = new CustomData<>();
        customData.setData(output);

        return customData;
    }
}
