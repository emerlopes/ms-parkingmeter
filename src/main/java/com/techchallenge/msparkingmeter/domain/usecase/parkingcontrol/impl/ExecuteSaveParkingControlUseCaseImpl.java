package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.application.exceptions.ParkingControlValidationException;
import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrol.IParkingControlDomainService;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteSaveParkingControlUseCase;
import com.techchallenge.msparkingmeter.infrastructure.msdrivers.IDriversClient;
import com.techchallenge.msparkingmeter.infrastructure.mspayments.IPaymentsClient;
import com.techchallenge.msparkingmeter.infrastructure.mspayments.dto.PaymentOptionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ExecuteSaveParkingControlUseCaseImpl implements IExecuteSaveParkingControlUseCase {

    private final IParkingControlDomainService parkingControlDomainService;

    private final IPaymentsClient paymentsClient;

    public ExecuteSaveParkingControlUseCaseImpl(IParkingControlDomainService parkingControlDomainService, IPaymentsClient paymentsClient) {
        this.parkingControlDomainService = parkingControlDomainService;
        this.paymentsClient = paymentsClient;
    }

    @Override
    public CustomData<ParkingControlDomainEntityOutput> execute(ParkingControlDomainEntityInput input) {
        final var periodTypeId = input.getPeriodType().getParkingControlPeriodId();
        final var durationInMinutes = input.getDurationInMinutes();

        if (periodTypeId.equals(PeriodTypeEnum.FIXED.getValue())) {
            if (durationInMinutes == null || durationInMinutes == 0) {
                throw new ParkingControlValidationException("Duration in minutes is required when period type is fixed");
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

        CustomData<ParkingControlDomainEntityOutput> customData = new CustomData<>();
        customData.setData(output);

        return customData;
    }

}
