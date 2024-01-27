package com.techchallenge.msparkingmeter.repositories.drivernotification;

import com.techchallenge.msparkingmeter.domain.entity.scheduler.SchedulerInput;
import com.techchallenge.msparkingmeter.domain.sevice.scheduler.IDriverNotificationDomainService;
import com.techchallenge.msparkingmeter.infrastructure.properties.Properties;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class DriverNotificationServiceImpl implements IDriverNotificationDomainService {

    private final Properties properties;

    public DriverNotificationServiceImpl(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void createScheduledNotification(SchedulerInput input) {

        Twilio.init(properties.getAccountSid(), properties.getAuthToken());

        final var scheduledTime = this.createScheduledTime(input);
        final var phoneNumber = this.formatPhoneNumber(input.getPhoneNumber());

        final var message = Message.creator(
                        new PhoneNumber(phoneNumber),
                        properties.getMessageServiceSid(),
                        input.getMessage())
                .setSendAt(
                        ZonedDateTime.of(scheduledTime, ZoneId.of("America/Sao_Paulo")))
                .setScheduleType(Message.ScheduleType.FIXED)
                .create();

        System.out.println(message.getSid());
    }

    private LocalDateTime createScheduledTime(SchedulerInput input) {
        final var dateTimeNow = input.getDateTimeNow();
        final var durationInMinutes = input.getDurationInMinutes();

        return dateTimeNow.plusMinutes(durationInMinutes - 15);
    }

    private String formatPhoneNumber(String phoneNumber) {
        return String.format("+55%s", phoneNumber);
    }
}
