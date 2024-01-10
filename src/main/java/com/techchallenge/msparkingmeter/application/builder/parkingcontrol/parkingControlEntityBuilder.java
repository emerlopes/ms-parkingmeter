package com.techchallenge.msparkingmeter.application.builder.parkingcontrol;

import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlEntity;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;

import java.time.LocalDateTime;

public class parkingControlEntityBuilder {

    private final ParkingControlEntity parkingControlEntity;

    public parkingControlEntityBuilder() {
        this.parkingControlEntity = new ParkingControlEntity();
    }

    public parkingControlEntityBuilder withStartTime(LocalDateTime startTime) {
        this.parkingControlEntity.setStartTime(startTime);
        return this;
    }

    public parkingControlEntityBuilder withDurationInMinutes(Integer durationInMinutes) {
        this.parkingControlEntity.setDurationInMinutes(durationInMinutes);
        return this;
    }

    public parkingControlEntityBuilder withPeriodType(ParkingControlPeriodTypeEntity periodType) {
        this.parkingControlEntity.setPeriodType(periodType);
        return this;
    }

    public ParkingControlEntity build() {
        return this.parkingControlEntity;
    }
}
