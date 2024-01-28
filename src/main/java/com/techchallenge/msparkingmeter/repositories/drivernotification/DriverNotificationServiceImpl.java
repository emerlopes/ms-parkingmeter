package com.techchallenge.msparkingmeter.repositories.drivernotification;

import com.techchallenge.msparkingmeter.domain.entity.scheduler.SchedulerInput;
import com.techchallenge.msparkingmeter.domain.sevice.scheduler.IDriverNotificationDomainService;
import com.techchallenge.msparkingmeter.infrastructure.properties.Properties;
import com.techchallenge.msparkingmeter.repositories.hashicorp.HashicorpClientService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class DriverNotificationServiceImpl implements IDriverNotificationDomainService {

    private final HashicorpClientService hashicorpClientService;

    public DriverNotificationServiceImpl(HashicorpClientService hashicorpClientService) {
        this.hashicorpClientService = hashicorpClientService;
    }

    @Override
    public void createScheduledNotification(SchedulerInput input) {

        final var secrets = hashicorpClientService.getHashiCorpSecret();

        Twilio.init(secrets.getAccountSid(), secrets.getAuthToken());

        final var scheduledTime = this.createScheduledTime(input);
        final var phoneNumber = this.formatPhoneNumber(input.getPhoneNumber());

        Message.creator(new PhoneNumber(phoneNumber),
                        secrets.getMessageServiceSid(),
                        input.getMessage())
                .setSendAt(
                        ZonedDateTime.of(scheduledTime, ZoneId.of("America/Sao_Paulo")))
                .setScheduleType(Message.ScheduleType.FIXED)
                .create();
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
