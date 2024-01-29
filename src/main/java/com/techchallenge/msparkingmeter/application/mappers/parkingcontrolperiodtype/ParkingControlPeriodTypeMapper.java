package com.techchallenge.msparkingmeter.application.mappers.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.builder.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityInputBuilder;
import com.techchallenge.msparkingmeter.application.builder.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutputBuilder;
import com.techchallenge.msparkingmeter.application.builder.parkingcontrolperiodtype.ParkingControlPeriodTypeEntityBuilder;
import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutput;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;

import java.util.ArrayList;
import java.util.List;

public class ParkingControlPeriodTypeMapper {


    public static ParkingControlPeriodTypeDomainEntityInput mapToParkingControlPeriodTypeDomainEntityInput(
            Long periodTypeId) {

        return new ParkingControlPeriodTypeDomainEntityInputBuilder()
                .withPeriodType(PeriodTypeEnum.valueOf(periodTypeId))
                .build();
    }

    public static ParkingControlPeriodTypeDomainEntityOutput mapToParkingControlPeriodTypeDomainEntityOutput(
            ParkingControlPeriodTypeEntity entity) {

        return new ParkingControlPeriodTypeDomainEntityOutputBuilder()
                .withParkingControlPeriodId(entity.getParkingControlPeriodId())
                .withPeriodType(entity.getPeriodType())
                .build();
    }

    public static List<ParkingControlPeriodTypeDomainEntityOutput> mapToParkingControlPeriodTypeDomainEntitiesOutput(List<ParkingControlPeriodTypeEntity> entity) {
        List<ParkingControlPeriodTypeDomainEntityOutput> outputs = new ArrayList<>();

        for (ParkingControlPeriodTypeEntity paymentOptionEntity : entity) {
            final var output = new ParkingControlPeriodTypeDomainEntityOutputBuilder()
                    .withParkingControlPeriodId(paymentOptionEntity.getParkingControlPeriodId())
                    .withPeriodType(paymentOptionEntity.getPeriodType())
                    .build();

            outputs.add(output);
        }

        return outputs;
    }

    public static ParkingControlPeriodTypeEntity mapFromOutputToParkingControlPeriodTypeEntity(
            ParkingControlPeriodTypeDomainEntityOutput output) {

        final var entity = new ParkingControlPeriodTypeEntityBuilder()
                .withPeriodType(output.getPeriodType())
                .build();

        entity.setParkingControlPeriodId(output.getParkingControlPeriodId());

        return entity;
    }

    public static ParkingControlPeriodTypeEntity mapToParkingControlPeriodTypeEntity(
            ParkingControlPeriodTypeDomainEntityInput input) {

        return new ParkingControlPeriodTypeEntityBuilder()
                .withPeriodType(input.getPeriodType())
                .build();
    }
}
