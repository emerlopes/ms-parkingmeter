package com.techchallenge.msparkingmeter.application.mappers.parkingcontrol;

import com.techchallenge.msparkingmeter.application.builder.parkingcontrol.ParkingControlDomainEntityInputBuilder;
import com.techchallenge.msparkingmeter.application.builder.parkingcontrol.ParkingControlDomainEntityOutputBuilder;
import com.techchallenge.msparkingmeter.application.builder.parkingcontrol.ParkingControlEntityBuilder;
import com.techchallenge.msparkingmeter.application.entrypoints.rest.parkingcontrol.dto.ParkingControlDTO;
import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class ParkingControlMapper {

    public static ParkingControlDomainEntityInput mapToParkingControlDomainEntityInput(
            Optional<Integer> durationInMinutes) {

        return new ParkingControlDomainEntityInputBuilder()
                .withStartTime(LocalDateTime.now())
                .withDurationInMinutes(durationInMinutes.orElse(0))
                .build();
    }

    public static ParkingControlDomainEntityOutput mapToParkingControlDomainEntityOutput(
            ParkingControlEntity entity) {

        return new ParkingControlDomainEntityOutputBuilder()
                .withParkingControlId(entity.getParkingControlId())
                .withStartTime(entity.getStartTime())
                .withDurationInMinutes(entity.getDurationInMinutes())
                .withPeriodType(entity.getPeriodType())
                .build();
    }

    public static ParkingControlEntity mapToParkingControlEntity(
            ParkingControlDomainEntityInput input) {

        return new ParkingControlEntityBuilder()
                .withStartTime(input.getStartTime())
                .withDurationInMinutes(input.getDurationInMinutes())
                .withPeriodType(input.getPeriodType())
                .build();
    }
}
