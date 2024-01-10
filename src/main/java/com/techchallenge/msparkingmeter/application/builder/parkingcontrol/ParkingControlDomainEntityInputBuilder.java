package com.techchallenge.msparkingmeter.application.builder.parkingcontrol;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;

public class ParkingControlDomainEntityInputBuilder {

    private final ParkingControlDomainEntityInput parkingControlDomainEntityInput;

    public ParkingControlDomainEntityInputBuilder() {
        this.parkingControlDomainEntityInput = new ParkingControlDomainEntityInput();
    }

    public ParkingControlDomainEntityInputBuilder withStartTime(java.time.LocalDateTime startTime) {
        this.parkingControlDomainEntityInput.setStartTime(startTime);
        return this;
    }

    public ParkingControlDomainEntityInputBuilder withDurationInMinutes(Integer durationInMinutes) {
        this.parkingControlDomainEntityInput.setDurationInMinutes(durationInMinutes);
        return this;
    }

    public ParkingControlDomainEntityInputBuilder withPeriodType(ParkingControlPeriodTypeEntity periodType) {
        this.parkingControlDomainEntityInput.setPeriodType(periodType);
        return this;
    }

    public ParkingControlDomainEntityInput build() {
        return this.parkingControlDomainEntityInput;
    }
}
