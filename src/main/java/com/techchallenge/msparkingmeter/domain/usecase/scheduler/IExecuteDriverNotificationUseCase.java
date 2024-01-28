package com.techchallenge.msparkingmeter.domain.usecase.scheduler;

import com.techchallenge.msparkingmeter.domain.entity.scheduler.SchedulerInput;
import com.techchallenge.msparkingmeter.domain.shared.IExecuteArgs;
import com.techchallenge.msparkingmeter.domain.shared.IExecuteVoid;

public interface IExecuteDriverNotificationUseCase extends IExecuteVoid<
        SchedulerInput> {
}
