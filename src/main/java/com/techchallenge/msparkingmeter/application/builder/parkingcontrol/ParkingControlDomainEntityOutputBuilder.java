package com.techchallenge.msparkingmeter.application.builder.parkingcontrol;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingControlDomainEntityOutputBuilder {

    private final ParkingControlDomainEntityOutput parkingControlDomainEntityOutput;

    public ParkingControlDomainEntityOutputBuilder() {
        this.parkingControlDomainEntityOutput = new ParkingControlDomainEntityOutput();
    }

    public ParkingControlDomainEntityOutputBuilder withParkingControlId(Long parkingControlId) {
        this.parkingControlDomainEntityOutput.setParkingControlId(parkingControlId);
        return this;
    }

    public ParkingControlDomainEntityOutputBuilder withExternalDriverId(UUID externalDriverId) {
        this.parkingControlDomainEntityOutput.setExternalDriverId(externalDriverId);
        return this;
    }

    public ParkingControlDomainEntityOutputBuilder withParkingStartTime(LocalDateTime startTime) {
        this.parkingControlDomainEntityOutput.setParkingStartTime(startTime);
        return this;
    }

    public ParkingControlDomainEntityOutputBuilder withParkingEndTime(LocalDateTime endTime) {
        this.parkingControlDomainEntityOutput.setParkingEndTime(endTime);
        return this;
    }

    public ParkingControlDomainEntityOutputBuilder withDurationInMinutes(Integer durationInMinutes) {
        this.parkingControlDomainEntityOutput.setDurationInMinutes(durationInMinutes);
        return this;
    }

    public ParkingControlDomainEntityOutputBuilder withValueToBePaid(BigDecimal valueToBePaid) {
        this.parkingControlDomainEntityOutput.setPredictedValueToBePaid(valueToBePaid);
        return this;
    }

    public ParkingControlDomainEntityOutputBuilder withFinalValueToBePaid(BigDecimal finalValueToBePaid) {
        this.parkingControlDomainEntityOutput.setFinalValueToBePaid(finalValueToBePaid);
        return this;
    }

    public ParkingControlDomainEntityOutputBuilder withPeriodType(ParkingControlPeriodTypeEntity periodType) {
        this.parkingControlDomainEntityOutput.setPeriodType(periodType);
        return this;
    }

    public ParkingControlDomainEntityOutput build() {
        return this.parkingControlDomainEntityOutput;
    }
}
