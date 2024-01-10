package com.techchallenge.msparkingmeter.application.builder.parkingcontrol;

import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlEntity;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;

import java.time.LocalDateTime;

public class ParkingControlEntityBuilder {

    private final ParkingControlEntity parkingControlEntity;

    public ParkingControlEntityBuilder() {
        this.parkingControlEntity = new ParkingControlEntity();
    }

    public ParkingControlEntityBuilder withStartTime(LocalDateTime startTime) {
        this.parkingControlEntity.setStartTime(startTime);
        return this;
    }

    public ParkingControlEntityBuilder withDurationInMinutes(Integer durationInMinutes) {
        this.parkingControlEntity.setDurationInMinutes(durationInMinutes);
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
