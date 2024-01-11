package com.techchallenge.msparkingmeter.domain.usecase.scheduler;

import com.techchallenge.msparkingmeter.domain.entity.scheduler.SchedulerInput;
import com.techchallenge.msparkingmeter.domain.shared.IExecuteArgs;

public interface IExecuteSchedulerUseCase extends IExecuteArgs<
        Void, SchedulerInput> {
}
