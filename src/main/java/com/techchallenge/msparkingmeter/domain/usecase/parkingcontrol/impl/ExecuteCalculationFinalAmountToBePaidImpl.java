package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.ExecuteCalculationFinalAmountToBePaid;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteFindParkingControlByIdUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ExecuteCalculationFinalAmountToBePaidImpl implements ExecuteCalculationFinalAmountToBePaid {

    private final IExecuteFindParkingControlByIdUseCase executeFindParkingControlByIdUseCase;
    private static final BigDecimal FIXED_PARKING_PRICE = BigDecimal.valueOf(2.0);
    private static final BigDecimal VARIABLE_PARKING_PRICE = BigDecimal.valueOf(5.0);

    public ExecuteCalculationFinalAmountToBePaidImpl(IExecuteFindParkingControlByIdUseCase executeFindParkingControlByIdUseCase) {
        this.executeFindParkingControlByIdUseCase = executeFindParkingControlByIdUseCase;
    }

    @Override
    public void execute(Long parkingControlId) {
        final var output = executeFindParkingControlByIdUseCase.execute(parkingControlId);
        final var parkingControlPeriodType = output.getData().getPeriodType();
        final var parkingStartTime = output.getData().getStartTime();
        final var parkingEndTime = LocalDateTime.now();
        final var fixedDurationInMinutes = output.getData().getDurationInMinutes();
        final var realDurationInMinutes = Duration.between(parkingStartTime, parkingEndTime).toMinutes();


        if (!PeriodTypeEnum.isFixed(parkingControlPeriodType.getParkingControlPeriodId())) {
            System.out.println("Period type is not fixed");

            final var chargedVariableHours = convertMinutesToHours((int) realDurationInMinutes);

            final var parkingPaymentAmount = VARIABLE_PARKING_PRICE.multiply(BigDecimal.valueOf(chargedVariableHours));

            System.out.println(parkingPaymentAmount);

        } else {
            System.out.println("Period type is fixed");

            final var chargedFixedHours = convertMinutesToHours(fixedDurationInMinutes);

            if (realDurationInMinutes > fixedDurationInMinutes) {
                System.out.println("Real duration is greater than fixed duration");
                final var chargedVariableHours = convertMinutesToHours((int) realDurationInMinutes - fixedDurationInMinutes);
                final var parkingPaymentAmount = FIXED_PARKING_PRICE.multiply(BigDecimal.valueOf(chargedFixedHours))
                        .add(VARIABLE_PARKING_PRICE.multiply(BigDecimal.valueOf(chargedVariableHours)));
                System.out.println(parkingPaymentAmount);
            }

            final var parkingPaymentAmount = FIXED_PARKING_PRICE.multiply(BigDecimal.valueOf(chargedFixedHours));

            System.out.println(parkingPaymentAmount);

        }

        System.out.println(output);

        // TODO: Atualizar a entidade: ParkingControlEntity com o valor final a ser pago e a data final do estacionamento
        // TODO: Criar um novo atributo para relacionar com o id recibo de pagamento
        // TODO: Criar a tabela de recibo de pagamento
        // TODO: Criar o endpoint para gerar o recibo de pagamento
        // TODO: Implementar a chamada para o endpoint de pagamento

    }

    private Integer convertMinutesToHours(int durationInMinutes) {
        return (int) Math.ceil(durationInMinutes / 60.0);
    }
}

