package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.shared.IExecuteArgs;
import com.techchallenge.msparkingmeter.domain.shared.IExecuteNoArgs;

import java.util.List;

public interface IExecuteFindAllParkingControlPeriodTypeUseCase extends IExecuteNoArgs<
        CustomData<List<ParkingControlPeriodTypeDomainEntityOutput>>> {
}
