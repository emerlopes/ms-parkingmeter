package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteFindParkingControlByIdUseCase;
import org.springframework.stereotype.Service;

@Service
public class ExecuteFindParkingControlByIdUseCaseImpl implements IExecuteFindParkingControlByIdUseCase {
    @Override
    public CustomData<ParkingControlDomainEntityOutput> execute(Long domainObject) {
        return null;
    }
}
