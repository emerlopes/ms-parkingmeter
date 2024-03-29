package com.techchallenge.msparkingmeter.application.mappers.parkingcontrol;

import com.techchallenge.msparkingmeter.application.builder.parkingcontrol.ParkingControlDomainEntityInputBuilder;
import com.techchallenge.msparkingmeter.application.builder.parkingcontrol.ParkingControlDomainEntityOutputBuilder;
import com.techchallenge.msparkingmeter.application.builder.parkingcontrol.ParkingControlEntityBuilder;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlEntity;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class ParkingControlMapper {

    public static ParkingControlDomainEntityInput mapToParkingControlDomainEntityInput(
            UUID externalDriverId,
            Optional<Integer> durationInMinutes) {

        return new ParkingControlDomainEntityInputBuilder()
                .withExternalDriverId(externalDriverId)
                .withStartTime(LocalDateTime.now())
                .withDurationInMinutes(durationInMinutes.orElse(0))
                .build();
    }

    public static ParkingControlDomainEntityOutput mapToParkingControlDomainEntityOutput(
            ParkingControlEntity entity) {

        return new ParkingControlDomainEntityOutputBuilder()
                .withParkingControlId(entity.getParkingControlId())
                .withExternalDriverId(entity.getExternalDriverId())
                .withParkingStartTime(entity.getParkingStartTime())
                .withParkingEndTime(entity.getParkingEndTime())
                .withRequestedMinutes(entity.getRequestedMinutes())
                .withRealMinutes(entity.getRealMinutes())
                .withValueToBePaid(entity.getPredictedValueToBePaid())
                .withFinalValueToBePaid(entity.getFinalValueToBePaid())
                .withPaymentReceiptId(entity.getPaymentReceiptId())
                .withPeriodType(entity.getPeriodType())
                .build();
    }

    public static ParkingControlEntity mapToParkingControlEntity(
            ParkingControlDomainEntityInput input) {

        return new ParkingControlEntityBuilder()
                .withExternalDriverId(input.getExternalDriverId())
                .withParkingStartTime(input.getStartTime())
                .withRequestedMinutes(input.getDurationInMinutes())
                .withPredictedValueToBePaid(input.getPredictedValueToBePaid())
                .withPeriodType(input.getPeriodType())
                .build();
    }

    public static ParkingControlEntity mapToParkingControlEntity(
            ParkingControlDomainEntityOutput input) {

        return new ParkingControlEntityBuilder()
                .withParkingControlId(input.getParkingControlId())
                .withExternalDriverId(input.getExternalDriverId())
                .withParkingStartTime(input.getParkingStartTime())
                .withParkingEndTime(input.getParkingEndTime())
                .withRequestedMinutes(input.getRequestedMinutes())
                .withRealMinutes(input.getRealMinutes())
                .withPredictedValueToBePaid(input.getPredictedValueToBePaid())
                .withFinalValueToBePaid(input.getFinalValueToBePaid())
                .withPaymentReceiptId(input.getPaymentReceiptId())
                .withPeriodType(input.getPeriodType())
                .build();
    }

}
