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

/**
 * Implementação da interface {@link IDriverNotificationDomainService} que lida com notificações para motoristas.
 */
@Service
public class DriverNotificationServiceImpl implements IDriverNotificationDomainService {

    private final HashicorpClientService hashicorpClientService;

    /**
     * Construtor da classe.
     *
     * @param hashicorpClientService O serviço HashiCorp para obter segredos.
     */
    public DriverNotificationServiceImpl(HashicorpClientService hashicorpClientService) {
        this.hashicorpClientService = hashicorpClientService;
    }

    /**
     * Cria uma notificação agendada para o motorista.
     *
     * @param input Os dados de entrada para a notificação agendada.
     */
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

    /**
     * Notifica imediatamente o motorista.
     *
     * @param input Os dados de entrada para a notificação imediata.
     */
    @Override
    public void notifyDriver(SchedulerInput input) {
        final var secrets = hashicorpClientService.getHashiCorpSecret();
        final var phoneNumber = this.formatPhoneNumber(input.getPhoneNumber());

        Twilio.init(secrets.getAccountSid(), secrets.getAuthToken());

        Message.creator(new PhoneNumber(phoneNumber),
                        secrets.getMessageServiceSid(),
                        input.getMessage())
                .create();
    }

    /**
     * Cria a hora agendada para a notificação com base na hora atual e na duração em minutos.
     *
     * @param input Os dados de entrada para a notificação agendada.
     * @return A hora agendada para a notificação.
     */
    private LocalDateTime createScheduledTime(SchedulerInput input) {
        final var dateTimeNow = input.getDateTimeNow();
        final var durationInMinutes = input.getDurationInMinutes();

        return dateTimeNow.plusMinutes(durationInMinutes - 15);
    }

    /**
     * Formata o número de telefone para o formato internacional.
     *
     * @param phoneNumber O número de telefone a ser formatado.
     * @return O número de telefone formatado no formato internacional.
     */
    private String formatPhoneNumber(String phoneNumber) {
        return String.format("+55%s", phoneNumber);
    }
}

