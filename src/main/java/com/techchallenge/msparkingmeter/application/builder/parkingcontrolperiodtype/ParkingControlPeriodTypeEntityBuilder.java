package com.techchallenge.msparkingmeter.application.builder.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;

public class ParkingControlPeriodTypeEntityBuilder {

    private final ParkingControlPeriodTypeEntity parkingControlPeriodTypeEntity;

    public ParkingControlPeriodTypeEntityBuilder() {
        this.parkingControlPeriodTypeEntity = new ParkingControlPeriodTypeEntity();
    }

    public ParkingControlPeriodTypeEntityBuilder withPeriodType(PeriodTypeEnum periodType) {
        this.parkingControlPeriodTypeEntity.setPeriodType(periodType);
        return this;
    }

    public ParkingControlPeriodTypeEntity build() {
        return this.parkingControlPeriodTypeEntity;
    }
}
