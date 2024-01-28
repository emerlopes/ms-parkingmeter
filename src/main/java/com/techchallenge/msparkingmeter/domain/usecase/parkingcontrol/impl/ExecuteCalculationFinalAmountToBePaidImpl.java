package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.PaymentReceipt;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrol.IParkingControlDomainService;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.ExecuteCalculationFinalAmountToBePaid;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteFindParkingControlByIdUseCase;
import com.techchallenge.msparkingmeter.repositories.mspayments.IPaymentsClient;
import com.techchallenge.msparkingmeter.repositories.mspayments.dto.ParkingPaymentReceiptDomainEntityInput;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ExecuteCalculationFinalAmountToBePaidImpl implements ExecuteCalculationFinalAmountToBePaid {

    private final IExecuteFindParkingControlByIdUseCase executeFindParkingControlByIdUseCase;
    private final IParkingControlDomainService parkingControlDomainService;
    private final IPaymentsClient paymentsClient;
    private static final BigDecimal FIXED_PARKING_PRICE = BigDecimal.valueOf(2.0);
    private static final BigDecimal VARIABLE_PARKING_PRICE = BigDecimal.valueOf(5.0);

    public ExecuteCalculationFinalAmountToBePaidImpl(IExecuteFindParkingControlByIdUseCase executeFindParkingControlByIdUseCase,
                                                     IParkingControlDomainService parkingControlDomainService, IPaymentsClient paymentsClient) {
        this.executeFindParkingControlByIdUseCase = executeFindParkingControlByIdUseCase;
        this.parkingControlDomainService = parkingControlDomainService;
        this.paymentsClient = paymentsClient;
    }

    @Override
    public CustomData<ParkingControlDomainEntityOutput> execute(Long parkingControlId) {
        final var output = executeFindParkingControlByIdUseCase.execute(parkingControlId);
        final var parkingControlPeriodType = output.getData().getPeriodType();
        final var parkingStartTime = output.getData().getParkingStartTime();
        final var parkingEndTime = LocalDateTime.now().plusMinutes(80);
        final var requestedMinutes = output.getData().getRequestedMinutes();
        final var realMinutes = Duration.between(parkingStartTime, parkingEndTime).toMinutes();
        var parkingTariff = BigDecimal.ZERO;

        final var domainEntity = output.getData();
        domainEntity.setParkingEndTime(parkingEndTime);
        domainEntity.setRealMinutes((int) realMinutes);

        if (!PeriodTypeEnum.isFixed(parkingControlPeriodType.getParkingControlPeriodId())) {
            System.out.println("Period type is not fixed");

            final var chargedVariableHours = roundDownToHours((int) realMinutes);

            final var parkingPaymentAmount = VARIABLE_PARKING_PRICE.multiply(BigDecimal.valueOf(chargedVariableHours));

            domainEntity.setFinalValueToBePaid(parkingPaymentAmount);

            parkingTariff = VARIABLE_PARKING_PRICE;

            System.out.println(parkingPaymentAmount);

        } else {
            System.out.println("Period type is fixed");

            final var chargedFixedHours = roundUpToHours(requestedMinutes);

            var parkingPaymentAmount = BigDecimal.ZERO;

            if (realMinutes > requestedMinutes) {
                System.out.println("Real duration is greater than fixed duration");
                final var chargedVariableHours = roundUpToHours((int) realMinutes - requestedMinutes);
                parkingPaymentAmount = FIXED_PARKING_PRICE.multiply(BigDecimal.valueOf(chargedFixedHours))
                        .add(VARIABLE_PARKING_PRICE.multiply(BigDecimal.valueOf(chargedVariableHours)));
                System.out.println(parkingPaymentAmount);
            } else {
                System.out.println("Real duration is less than fixed duration");
                parkingPaymentAmount = FIXED_PARKING_PRICE.multiply(BigDecimal.valueOf(chargedFixedHours));
            }

            domainEntity.setFinalValueToBePaid(parkingPaymentAmount);

            parkingTariff = FIXED_PARKING_PRICE;

            System.out.println(parkingPaymentAmount);

        }

        System.out.println(output);

        final var paymentReceiptInput = ParkingPaymentReceiptDomainEntityInput
                .builder()
                .paymentDate(LocalDateTime.now())
                .paymentAmount(domainEntity.getFinalValueToBePaid())
                .build();

        final var paymentType = paymentsClient.findPaymentOptionByExternalDriverId(domainEntity.getExternalDriverId());

        paymentReceiptInput.setPaymentMethod(paymentType.getData().getPaymentOptionType().getPaymentOptionType().getDisplayName());

        final var paymentReceiptOutput = paymentsClient.savePaymentReceipt(paymentReceiptInput);

        domainEntity.setPaymentReceiptId(paymentReceiptOutput.getData().getReceiptNumber());

        final var entitySaved = parkingControlDomainService.saveParkingControl(domainEntity);

        final var paymentReceipy = PaymentReceipt.builder()
                .parkingStartTime(parkingStartTime)
                .parkingEndTime(parkingEndTime)
                .parkingMinutes((int) realMinutes)
                .parkingTariff(parkingTariff)
                .totalPaymentAmount(domainEntity.getFinalValueToBePaid())
                .build();

        entitySaved.setPaymentReceipt(paymentReceipy);

        final CustomData<ParkingControlDomainEntityOutput> customData = new CustomData<>();
        customData.setData(entitySaved);

        System.out.println(paymentReceiptOutput);

        return customData;
    }

    private Integer roundUpToHours(int minutes) {
        return (int) Math.ceil(minutes / 60.0);
    }

    private Integer roundDownToHours(int minutes) {
        return (int) Math.floor(minutes / 60.0);
    }
}

