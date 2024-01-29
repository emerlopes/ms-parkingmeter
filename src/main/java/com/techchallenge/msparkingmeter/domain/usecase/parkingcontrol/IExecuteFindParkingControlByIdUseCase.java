package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.shared.IExecuteArgs;

public interface IExecuteFindParkingControlByIdUseCase extends IExecuteArgs<
        CustomData<ParkingControlDomainEntityOutput>, Long> {
}
