package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.shared.IExecuteArgs;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlEntity;

public interface IExecuteUpdateParkingControlUseCase extends IExecuteArgs<
        CustomData<ParkingControlDomainEntityOutput>, ParkingControlEntity> {
}
