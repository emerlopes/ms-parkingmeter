package com.techchallenge.msparkingmeter.domain.usecase.scheduler.impl;

import com.techchallenge.msparkingmeter.domain.entity.scheduler.SchedulerInput;
import com.techchallenge.msparkingmeter.domain.sevice.scheduler.ISchedulerDomainService;
import com.techchallenge.msparkingmeter.domain.usecase.scheduler.IExecuteSchedulerUseCase;
import org.springframework.stereotype.Service;

@Service
public class ExecuteSchedulerUseCaseImpl implements IExecuteSchedulerUseCase {

    private final ISchedulerDomainService schedulerDomainService;

    public ExecuteSchedulerUseCaseImpl(ISchedulerDomainService schedulerDomainService) {
        this.schedulerDomainService = schedulerDomainService;
    }

    @Override
    public Void execute(SchedulerInput input) {

        schedulerDomainService.createNotificationSchedule(input);
        return null;
    }
}
