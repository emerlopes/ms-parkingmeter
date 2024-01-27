package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteUpdateParkingControlUseCase;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlEntity;
import org.springframework.stereotype.Service;

@Service
public class ExecuteUpdateParkingControlUseCaseImpl implements IExecuteUpdateParkingControlUseCase {

    @Override
    public CustomData<ParkingControlDomainEntityOutput> execute(ParkingControlEntity domainObject) {
        return null;
    }
}
