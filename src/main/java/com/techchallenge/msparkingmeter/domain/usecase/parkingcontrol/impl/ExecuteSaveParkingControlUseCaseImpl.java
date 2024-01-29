package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.application.exceptions.ParkingControlValidationException;
import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.businessrules.composites.impl.ParkingmeterBusinessRulesValidatorCompositeImpl;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.entity.scheduler.SchedulerInput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrol.IParkingControlDomainService;
import com.techchallenge.msparkingmeter.domain.sevice.scheduler.IDriverNotificationDomainService;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteSaveParkingControlUseCase;
import com.techchallenge.msparkingmeter.repositories.msdrivers.IDriversClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementação da interface {@link IExecuteSaveParkingControlUseCase} responsável por executar a lógica de salvar um controle de estacionamento.
 */
@Service
public class ExecuteSaveParkingControlUseCaseImpl implements IExecuteSaveParkingControlUseCase {

    /**
     * Serviço de domínio para controle de estacionamento.
     */
    private final IParkingControlDomainService parkingControlDomainService;

    /**
     * Validador de regras de negócio do estacionamento.
     */
    private final ParkingmeterBusinessRulesValidatorCompositeImpl parkingmeterBusinessRulesValidator;

    /**
     * Serviço de notificação para motoristas.
     */
    private final IDriverNotificationDomainService schedulerDomainService;

    /**
     * Cliente para acessar informações de motoristas.
     */
    private final IDriversClient driversClient;

    /**
     * Construtor da classe.
     *
     * @param parkingControlDomainService        Serviço de domínio para controle de estacionamento.
     * @param parkingmeterBusinessRulesValidator Validador de regras de negócio do estacionamento.
     * @param schedulerDomainService             Serviço de notificação para motoristas.
     * @param driversClient                      Cliente para acessar informações de motoristas.
     */
    public ExecuteSaveParkingControlUseCaseImpl(IParkingControlDomainService parkingControlDomainService,
                                                ParkingmeterBusinessRulesValidatorCompositeImpl parkingmeterBusinessRulesValidator,
                                                IDriverNotificationDomainService schedulerDomainService,
                                                IDriversClient driversClient) {
        this.parkingControlDomainService = parkingControlDomainService;
        this.parkingmeterBusinessRulesValidator = parkingmeterBusinessRulesValidator;
        this.schedulerDomainService = schedulerDomainService;
        this.driversClient = driversClient;
    }

    /**
     * Executa a lógica de salvar um controle de estacionamento.
     *
     * @param input Dados de entrada para o controle de estacionamento.
     * @return Dados de saída do controle de estacionamento após a execução.
     * @throws ParkingControlValidationException Exceção lançada se a validação do controle de estacionamento falhar.
     */
    @Override
    public CustomData<ParkingControlDomainEntityOutput> execute(ParkingControlDomainEntityInput input) throws ParkingControlValidationException {
        this.checkParkingmeterBusinessRules(input);
        this.findDriverById(input);
        final var periodTypeMessage = input.getPeriodType().getPeriodType().getMessage();
        final var output = parkingControlDomainService.saveParkingControl(input);
        final var schedulerInput = this.createSchedulerInput(input, periodTypeMessage);

        if (input.getPeriodType().getPeriodType().equals(PeriodTypeEnum.FIXED)) {
            schedulerDomainService.createScheduledNotification(schedulerInput);
        } else {
            schedulerDomainService.notifyDriver(schedulerInput);
        }

        CustomData<ParkingControlDomainEntityOutput> customData = new CustomData<>();
        customData.setData(output);

        return customData;
    }

    /**
     * Verifica as regras de negócio do estacionamento.
     *
     * @param input Dados de entrada para o controle de estacionamento.
     * @throws ParkingControlValidationException Exceção lançada se a validação do controle de estacionamento falhar.
     */
    private void checkParkingmeterBusinessRules(ParkingControlDomainEntityInput input) throws ParkingControlValidationException {
        final var isParkingmeterBusinessRules = parkingmeterBusinessRulesValidator.isValid(input);

        if (!isParkingmeterBusinessRules) {
            throw parkingmeterBusinessRulesValidator.getException();
        }
    }

    /**
     * Encontra e associa o motorista pelo ID externo.
     *
     * @param input Dados de entrada para o controle de estacionamento.
     * @return Dados de entrada para o controle de estacionamento com o motorista associado.
     * @throws ParkingControlValidationException Exceção lançada se o motorista não for encontrado.
     */
    private ParkingControlDomainEntityInput findDriverById(ParkingControlDomainEntityInput input) throws ParkingControlValidationException {
        final var externalDriverId = input.getExternalDriverId();
        final var driver = driversClient.findDriverById(externalDriverId);

        if (driver.getData() == null) {
            throw new ParkingControlValidationException("Motorista não encontrado");
        }

        input.setDriver(driver.getData());

        return input;
    }

    /**
     * Cria os dados de entrada para o agendamento ou notificação do motorista.
     *
     * @param input             Dados de entrada para o controle de estacionamento.
     * @param periodTypeMessage Mensagem do tipo de período do estacionamento.
     * @return Dados de entrada para o agendamento ou notificação do motorista.
     */
    private SchedulerInput createSchedulerInput(ParkingControlDomainEntityInput input, String periodTypeMessage) {
        final var schedulerInput = new SchedulerInput();
        schedulerInput.setExternalDriverId(input.getExternalDriverId());
        schedulerInput.setPhoneNumber(input.getDriver().getPhoneNumber());
        schedulerInput.setDateTimeNow(LocalDateTime.now());
        schedulerInput.setDurationInMinutes(input.getDurationInMinutes());
        schedulerInput.setMessage(periodTypeMessage);

        return schedulerInput;
    }

}
