package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrol.IParkingControlDomainService;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.ExecuteCalculationFinalAmountToBePaid;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteFindParkingControlByIdUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ExecuteCalculationFinalAmountToBePaidImpl implements ExecuteCalculationFinalAmountToBePaid {

    private final IExecuteFindParkingControlByIdUseCase executeFindParkingControlByIdUseCase;
    private final IParkingControlDomainService parkingControlDomainService;
    private static final BigDecimal FIXED_PARKING_PRICE = BigDecimal.valueOf(2.0);
    private static final BigDecimal VARIABLE_PARKING_PRICE = BigDecimal.valueOf(5.0);

    public ExecuteCalculationFinalAmountToBePaidImpl(IExecuteFindParkingControlByIdUseCase executeFindParkingControlByIdUseCase,
                                                     IParkingControlDomainService parkingControlDomainService) {
        this.executeFindParkingControlByIdUseCase = executeFindParkingControlByIdUseCase;
        this.parkingControlDomainService = parkingControlDomainService;
    }

    @Override
    public CustomData<ParkingControlDomainEntityOutput> execute(Long parkingControlId) {
        final var output = executeFindParkingControlByIdUseCase.execute(parkingControlId);
        final var parkingControlPeriodType = output.getData().getPeriodType();
        final var parkingStartTime = output.getData().getParkingStartTime();
        final var parkingEndTime = LocalDateTime.now().plusMinutes(189);
        final var requestedMinutes = output.getData().getRequestedMinutes();
        final var realMinutes = Duration.between(parkingStartTime, parkingEndTime).toMinutes();

        final var domainEntity = output.getData();
        domainEntity.setParkingEndTime(parkingEndTime);
        domainEntity.setRealMinutes((int) realMinutes);

        if (!PeriodTypeEnum.isFixed(parkingControlPeriodType.getParkingControlPeriodId())) {
            System.out.println("Period type is not fixed");

            final var chargedVariableHours = convertMinutesToHours((int) realMinutes);

            final var parkingPaymentAmount = VARIABLE_PARKING_PRICE.multiply(BigDecimal.valueOf(chargedVariableHours));

            domainEntity.setFinalValueToBePaid(parkingPaymentAmount);

            System.out.println(parkingPaymentAmount);

        } else {
            System.out.println("Period type is fixed");

            final var chargedFixedHours = convertMinutesToHours(requestedMinutes);

            var parkingPaymentAmount = BigDecimal.ZERO;

            if (realMinutes > requestedMinutes) {
                System.out.println("Real duration is greater than fixed duration");
                final var chargedVariableHours = convertMinutesToHours((int) realMinutes - requestedMinutes);
                parkingPaymentAmount = FIXED_PARKING_PRICE.multiply(BigDecimal.valueOf(chargedFixedHours))
                        .add(VARIABLE_PARKING_PRICE.multiply(BigDecimal.valueOf(chargedVariableHours)));
                System.out.println(parkingPaymentAmount);
            } else {
                System.out.println("Real duration is less than fixed duration");
                parkingPaymentAmount = FIXED_PARKING_PRICE.multiply(BigDecimal.valueOf(chargedFixedHours));
            }

            domainEntity.setFinalValueToBePaid(parkingPaymentAmount);

            System.out.println(parkingPaymentAmount);

        }

        System.out.println(output);

        // TODO: Atualizar a entidade: ParkingControlEntity com o valor final a ser pago e a data final do estacionamento
        final var entitySaved = parkingControlDomainService.saveParkingControl(domainEntity);

        // TODO: Criar um novo atributo para relacionar com o id recibo de pagamento
        // TODO: Criar a tabela de recibo de pagamento
        // TODO: Criar o endpoint para gerar o recibo de pagamento
        // TODO: Implementar a chamada para o endpoint de pagamento

        final CustomData<ParkingControlDomainEntityOutput> customData = new CustomData<>();
        customData.setData(entitySaved);

        return customData;
    }

    private Integer convertMinutesToHours(int durationInMinutes) {
        return (int) Math.ceil(durationInMinutes / 60.0);
    }
}

