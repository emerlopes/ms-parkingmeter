package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.ExecuteCalculationFixedAmountExpectedToBePaidUseCaseImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Implementação do caso de uso para calcular o valor esperado a ser pago pelo estacionamento.
 */
@Service
public class ExecuteCalculationAmountExpectedToBePaidUseCaseImpl implements ExecuteCalculationFixedAmountExpectedToBePaidUseCaseImpl {

    private static final BigDecimal FIXED_PARKING_PRICE = BigDecimal.valueOf(2.0);

    /**
     * Executa o cálculo do valor esperado a ser pago pelo estacionamento com base nas informações fornecidas.
     *
     * @param input Entrada de dados do controle de estacionamento contendo informações sobre a duração, tipo de período, etc.
     */
    @Override
    public void execute(ParkingControlDomainEntityInput input) {

        final var durationInMinutes = input.getDurationInMinutes();
        final var hours = convertMinutesToHours(durationInMinutes);
        final var periodType = input.getPeriodType();

        if (PeriodTypeEnum.isFixed(periodType.getParkingControlPeriodId())) {

            final var predictedValueToBePaid = FIXED_PARKING_PRICE.multiply(BigDecimal.valueOf(hours));

            input.setPredictedValueToBePaid(predictedValueToBePaid);
        }
    }

    /**
     * Converte a duração em minutos para horas, arredondando para cima.
     *
     * @param durationInMinutes Duração em minutos a ser convertida.
     * @return O número de horas equivalente à duração em minutos.
     */
    private Integer convertMinutesToHours(int durationInMinutes) {
        return (int) Math.ceil(durationInMinutes / 60.0);
    }
}
