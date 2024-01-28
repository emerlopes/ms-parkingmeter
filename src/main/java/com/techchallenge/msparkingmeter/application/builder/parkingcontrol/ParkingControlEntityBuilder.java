package com.techchallenge.msparkingmeter.application.builder.parkingcontrol;

import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlEntity;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingControlEntityBuilder {

    private final ParkingControlEntity parkingControlEntity;

    public ParkingControlEntityBuilder() {
        this.parkingControlEntity = new ParkingControlEntity();
    }

    public ParkingControlEntityBuilder withParkingControlId(Long parkingControlId) {
        this.parkingControlEntity.setParkingControlId(parkingControlId);
        return this;
    }

    public ParkingControlEntityBuilder withExternalDriverId(UUID externalDriverId) {
        this.parkingControlEntity.setExternalDriverId(externalDriverId);
        return this;
    }

    public ParkingControlEntityBuilder withParkingStartTime(LocalDateTime parkingStartTime) {
        this.parkingControlEntity.setParkingStartTime(parkingStartTime);
        return this;
    }

    public ParkingControlEntityBuilder withParkingEndTime(LocalDateTime parkingEndTime) {
        this.parkingControlEntity.setParkingEndTime(parkingEndTime);
        return this;
    }

    public ParkingControlEntityBuilder withDurationInMinutes(Integer durationInMinutes) {
        this.parkingControlEntity.setDurationInMinutes(durationInMinutes);
        return this;
    }

    public ParkingControlEntityBuilder withPredictedValueToBePaid(BigDecimal predictedValueToBePaid) {
        this.parkingControlEntity.setPredictedValueToBePaid(predictedValueToBePaid);
        return this;
    }

    public ParkingControlEntityBuilder withFinalValueToBePaid(BigDecimal finalValueToBePaid) {
        this.parkingControlEntity.setFinalValueToBePaid(finalValueToBePaid);
        return this;
    }

    public ParkingControlEntityBuilder withPeriodType(ParkingControlPeriodTypeEntity periodType) {
        this.parkingControlEntity.setPeriodType(periodType);
        return this;
    }

    public ParkingControlEntity build() {
        return this.parkingControlEntity;
    }
}
