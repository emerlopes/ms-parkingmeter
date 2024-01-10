package com.techchallenge.msparkingmeter.application.builder.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutput;

public class ParkingControlPeriodTypeDomainEntityOutputBuilder {

    private final ParkingControlPeriodTypeDomainEntityOutput parkingControlPeriodTypeDomainEntityOutput;

    public ParkingControlPeriodTypeDomainEntityOutputBuilder() {
        this.parkingControlPeriodTypeDomainEntityOutput = new ParkingControlPeriodTypeDomainEntityOutput();
    }

    public ParkingControlPeriodTypeDomainEntityOutputBuilder withParkingControlPeriodId(Long parkingControlPeriodId) {
        this.parkingControlPeriodTypeDomainEntityOutput.setParkingControlPeriodId(parkingControlPeriodId);
        return this;
    }

    public ParkingControlPeriodTypeDomainEntityOutputBuilder withPeriodType(PeriodTypeEnum periodType) {
        this.parkingControlPeriodTypeDomainEntityOutput.setPeriodType(periodType);
        return this;
    }

    public ParkingControlPeriodTypeDomainEntityOutput build() {
        return this.parkingControlPeriodTypeDomainEntityOutput;
    }
}
