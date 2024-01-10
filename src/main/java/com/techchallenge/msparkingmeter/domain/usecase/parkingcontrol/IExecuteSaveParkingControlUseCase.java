package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.shared.IExecuteArgs;

public interface IExecuteSaveParkingControlUseCase extends IExecuteArgs<
        CustomData<ParkingControlDomainEntityOutput>, ParkingControlDomainEntityInput> {
}
