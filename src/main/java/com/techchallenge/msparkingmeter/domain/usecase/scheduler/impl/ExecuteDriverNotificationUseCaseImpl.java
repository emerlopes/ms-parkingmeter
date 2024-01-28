package com.techchallenge.msparkingmeter.domain.usecase.scheduler.impl;

import com.techchallenge.msparkingmeter.domain.entity.scheduler.SchedulerInput;
import com.techchallenge.msparkingmeter.domain.sevice.scheduler.IDriverNotificationDomainService;
import com.techchallenge.msparkingmeter.domain.usecase.scheduler.IExecuteDriverNotificationUseCase;
import org.springframework.stereotype.Service;

@Service
public class ExecuteDriverNotificationUseCaseImpl implements IExecuteDriverNotificationUseCase {

    private final IDriverNotificationDomainService schedulerDomainService;

    public ExecuteDriverNotificationUseCaseImpl(IDriverNotificationDomainService schedulerDomainService) {
        this.schedulerDomainService = schedulerDomainService;
    }

    @Override
    public void execute(SchedulerInput input) {
        schedulerDomainService.createScheduledNotification(input);
    }
}
