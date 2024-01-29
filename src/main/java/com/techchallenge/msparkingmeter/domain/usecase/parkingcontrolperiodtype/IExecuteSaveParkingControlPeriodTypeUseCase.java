package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.shared.IExecuteArgs;

public interface IExecuteSaveParkingControlPeriodTypeUseCase extends IExecuteArgs<
        CustomData<ParkingControlPeriodTypeDomainEntityOutput>, ParkingControlPeriodTypeDomainEntityInput> {
}
