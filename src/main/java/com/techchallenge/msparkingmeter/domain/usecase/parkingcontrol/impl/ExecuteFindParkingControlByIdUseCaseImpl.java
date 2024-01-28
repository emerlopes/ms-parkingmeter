package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrol.IParkingControlDomainService;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteFindParkingControlByIdUseCase;
import org.springframework.stereotype.Service;

@Service
public class ExecuteFindParkingControlByIdUseCaseImpl implements IExecuteFindParkingControlByIdUseCase {

    final private IParkingControlDomainService parkingControlDomainService;

    public ExecuteFindParkingControlByIdUseCaseImpl(IParkingControlDomainService parkingControlDomainService) {
        this.parkingControlDomainService = parkingControlDomainService;
    }

    @Override
    public CustomData<ParkingControlDomainEntityOutput> execute(Long domainObject) {
        final var output = parkingControlDomainService.findParkingControlById(domainObject);

        CustomData<ParkingControlDomainEntityOutput> customData = new CustomData<>();
        customData.setData(output);
        return customData;
    }
}
