package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.application.exceptions.ParkingControlValidationException;
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

@Service
public class ExecuteSaveParkingControlUseCaseImpl implements IExecuteSaveParkingControlUseCase {

    private final IParkingControlDomainService parkingControlDomainService;

    private final ParkingmeterBusinessRulesValidatorCompositeImpl parkingmeterBusinessRulesValidator;

    private final IDriverNotificationDomainService schedulerDomainService;

    private final IDriversClient driversClient;

    public ExecuteSaveParkingControlUseCaseImpl(IParkingControlDomainService parkingControlDomainService,
                                                ParkingmeterBusinessRulesValidatorCompositeImpl parkingmeterBusinessRulesValidator,
                                                IDriverNotificationDomainService schedulerDomainService,
                                                IDriversClient driversClient) {
        this.parkingControlDomainService = parkingControlDomainService;
        this.parkingmeterBusinessRulesValidator = parkingmeterBusinessRulesValidator;
        this.schedulerDomainService = schedulerDomainService;
        this.driversClient = driversClient;
    }

    @Override
    public CustomData<ParkingControlDomainEntityOutput> execute(ParkingControlDomainEntityInput input) {

        this.checkParkingmeterBusinessRules(input);
        this.findDriverById(input);

        final var periodTypeMessage = input.getPeriodType().getPeriodType().getMessage();
        final var output = parkingControlDomainService.saveParkingControl(input);

        final var schedulerInput = this.createSchedulerInput(input, periodTypeMessage);

//        schedulerDomainService.createScheduledNotification(schedulerInput);

        CustomData<ParkingControlDomainEntityOutput> customData = new CustomData<>();
        customData.setData(output);

        return customData;
    }

    private void checkParkingmeterBusinessRules(ParkingControlDomainEntityInput input) {
        final var isParkingmeterBusinessRules = parkingmeterBusinessRulesValidator.isValid(input);

        if (!isParkingmeterBusinessRules) {
            throw parkingmeterBusinessRulesValidator.getException();
        }
    }

    private ParkingControlDomainEntityInput findDriverById(ParkingControlDomainEntityInput input) {
        final var externalDriverId = input.getExternalDriverId();
        final var driver = driversClient.findDriverById(externalDriverId);

        if (driver.getData() == null) {
            throw new ParkingControlValidationException("Driver not found");
        }

        input.setDriver(driver.getData());

        return input;

    }

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
