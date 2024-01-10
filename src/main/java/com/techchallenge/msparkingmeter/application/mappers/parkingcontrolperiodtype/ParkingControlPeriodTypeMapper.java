package com.techchallenge.msparkingmeter.application.mappers.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.builder.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityInputBuilder;
import com.techchallenge.msparkingmeter.application.builder.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutputBuilder;
import com.techchallenge.msparkingmeter.application.builder.parkingcontrolperiodtype.ParkingControlPeriodTypeEntityBuilder;
import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutput;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;

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

    public static ParkingControlPeriodTypeEntity mapToParkingControlPeriodTypeEntity(
            ParkingControlPeriodTypeDomainEntityInput input) {

        return new ParkingControlPeriodTypeEntityBuilder()
                .withPeriodType(input.getPeriodType())
                .build();
    }
}
