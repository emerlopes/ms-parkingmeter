package com.techchallenge.msparkingmeter.domain.sevice.scheduler;

import com.techchallenge.msparkingmeter.domain.entity.scheduler.SchedulerInput;

public interface IDriverNotificationDomainService {
    void createScheduledNotification(SchedulerInput input);

}
