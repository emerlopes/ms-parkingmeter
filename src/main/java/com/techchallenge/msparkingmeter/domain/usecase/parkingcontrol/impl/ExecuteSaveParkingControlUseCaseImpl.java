package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.application.exceptions.ParkingControlValidationException;
import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.entity.scheduler.SchedulerInput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrol.IParkingControlDomainService;
import com.techchallenge.msparkingmeter.domain.sevice.scheduler.ISchedulerDomainService;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteSaveParkingControlUseCase;
import com.techchallenge.msparkingmeter.infrastructure.msdrivers.IDriversClient;
import com.techchallenge.msparkingmeter.infrastructure.mspayments.IPaymentsClient;
import com.techchallenge.msparkingmeter.infrastructure.mspayments.dto.PaymentOptionTypeEnum;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class ExecuteSaveParkingControlUseCaseImpl implements IExecuteSaveParkingControlUseCase {

    private final IParkingControlDomainService parkingControlDomainService;

    private final ISchedulerDomainService schedulerDomainService;

    private final IPaymentsClient paymentsClient;

    private final IDriversClient driversClient;

    private final static int MINIMUM_DURATION_IN_MINUTES = 60;

    public ExecuteSaveParkingControlUseCaseImpl(IParkingControlDomainService parkingControlDomainService, ISchedulerDomainService schedulerDomainService, IPaymentsClient paymentsClient, IDriversClient driversClient) {
        this.parkingControlDomainService = parkingControlDomainService;
        this.schedulerDomainService = schedulerDomainService;
        this.paymentsClient = paymentsClient;
        this.driversClient = driversClient;
    }

    @Override
    public CustomData<ParkingControlDomainEntityOutput> execute(ParkingControlDomainEntityInput input) {
        final var externalDriverId = input.getExternalDriverId();
        final var periodTypeId = input.getPeriodType().getParkingControlPeriodId();
        final var durationInMinutes = input.getDurationInMinutes();

        final var driver = driversClient.findDriverById(externalDriverId);

        if (driver.getData() == null) {
            throw new ParkingControlValidationException("Driver not found");
        }

        input.setDriver(driver.getData());

        if (periodTypeId.equals(PeriodTypeEnum.FIXED.getValue())) {
            if (durationInMinutes == null || durationInMinutes == 0) {
                throw new ParkingControlValidationException("Duration in minutes is required when period type is fixed");
            }

            if (durationInMinutes < MINIMUM_DURATION_IN_MINUTES) {
                throw new ParkingControlValidationException("The minimum duration for a fixed parking period is 60 minutes");
            }

            final var paymentOption = paymentsClient.findPaymentOptionByExternalDriverId(input.getExternalDriverId());

            if (paymentOption.getData() == null) {
                throw new ParkingControlValidationException("Payment option not found");
            }

            final var paymentOptionType = paymentOption.getData().getPaymentOptionType().getPaymentOptionType();

            if (paymentOptionType.equals(PaymentOptionTypeEnum.CREDIT_CARD) || paymentOptionType.equals(PaymentOptionTypeEnum.DEBIT_CARD)) {
                throw new ParkingControlValidationException("The fixed parking period is only available for payments via PIX, please update your payment method.");
            }

        }

        if (Objects.equals(periodTypeId, PeriodTypeEnum.VARIABLE.getValue())) {
            input.setDurationInMinutes(0);
        }

        final var output = parkingControlDomainService.saveParkingControl(input);

        schedulerDomainService.createNotificationSchedule(this.createSchedulerInput(input));

        CustomData<ParkingControlDomainEntityOutput> customData = new CustomData<>();
        customData.setData(output);

        return customData;
    }

    private SchedulerInput createSchedulerInput(ParkingControlDomainEntityInput input) {
        final var schedulerInput = new SchedulerInput();
        schedulerInput.setExternalDriverId(input.getExternalDriverId());
        schedulerInput.setPhoneNumber(input.getDriver().getPhoneNumber());
        schedulerInput.setDateTimeNow(LocalDateTime.now());
        schedulerInput.setDurationInMinutes(input.getDurationInMinutes());

        return schedulerInput;
    }

}
