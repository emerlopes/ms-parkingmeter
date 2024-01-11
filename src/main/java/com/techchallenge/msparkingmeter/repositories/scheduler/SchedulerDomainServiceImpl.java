package com.techchallenge.msparkingmeter.repositories.scheduler;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.scheduler.AmazonScheduler;
import com.amazonaws.services.scheduler.AmazonSchedulerClientBuilder;
import com.amazonaws.services.scheduler.model.CreateScheduleRequest;
import com.amazonaws.services.scheduler.model.FlexibleTimeWindow;
import com.amazonaws.services.scheduler.model.Target;
import com.techchallenge.msparkingmeter.domain.entity.scheduler.SchedulerInput;
import com.techchallenge.msparkingmeter.domain.sevice.scheduler.ISchedulerDomainService;
import com.techchallenge.msparkingmeter.infrastructure.properties.Properties;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SchedulerDomainServiceImpl implements ISchedulerDomainService {

    private final Properties properties;

    AmazonScheduler schedulerClient = AmazonSchedulerClientBuilder
            .standard()
            .withCredentials(new ProfileCredentialsProvider())
            .build();

    public SchedulerDomainServiceImpl(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void createNotificationSchedule(SchedulerInput input) {
        final var target = this.createTarget(input);
        final var scheduleExpression = this.creteCronExpression(input);
        final var externalDriverId = input.getExternalDriverId();
        final var scheduleRequest = this.createScheduleRequest(externalDriverId, target, scheduleExpression);

        System.out.println(externalDriverId);

        schedulerClient.createSchedule(scheduleRequest);
    }

    private String creteCronExpression(SchedulerInput input) {
        final var dateTimeNow = input.getDateTimeNow();
        final var durationInMinutes = input.getDurationInMinutes();

        final var timeNotification = dateTimeNow.plusMinutes(durationInMinutes - 20);

        int minute = timeNotification.getMinute();
        int hour = timeNotification.getHour();
        int dayOfMonth = timeNotification.getDayOfMonth();
        int month = timeNotification.getMonthValue();
        int year = timeNotification.getYear();

        String cronExpression = String.format("cron(%d %d %d %d ? %d)", minute, hour, dayOfMonth, month, year);

        System.out.println(cronExpression);

        return cronExpression;
    }

    private Target createTarget(SchedulerInput input) {
        final var inputMessage = input.getExternalDriverId().toString();

        return new Target()
                .withRoleArn(properties.getSchedulerRoleArn())
                .withArn(properties.getSqsDriverNotificationArn())
                .withInput(inputMessage);
    }

    private CreateScheduleRequest createScheduleRequest(
            UUID externalDriverId, Target target, String scheduleExpression) {
        CreateScheduleRequest scheduleRequest = new CreateScheduleRequest()
                .withName("external-driver-id-" + externalDriverId.toString())
                .withScheduleExpression(scheduleExpression)
                .withDescription(externalDriverId.toString())
                .withFlexibleTimeWindow(new FlexibleTimeWindow().withMode("OFF"))
                .withTarget(target);

        scheduleRequest.setScheduleExpressionTimezone(properties.getLocalZoneSp());
        scheduleRequest.setActionAfterCompletion("DELETE");

        return scheduleRequest;

    }
}
