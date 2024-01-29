package com.techchallenge.msparkingmeter.application.builder.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityInput;

public class ParkingControlPeriodTypeDomainEntityInputBuilder {

    private final ParkingControlPeriodTypeDomainEntityInput parkingControlPeriodTypeDomainEntityInput;

    public ParkingControlPeriodTypeDomainEntityInputBuilder() {
        this.parkingControlPeriodTypeDomainEntityInput = new ParkingControlPeriodTypeDomainEntityInput();
    }

    public ParkingControlPeriodTypeDomainEntityInputBuilder withPeriodType(PeriodTypeEnum periodType) {
        this.parkingControlPeriodTypeDomainEntityInput.setPeriodType(periodType);
        return this;
    }

    public ParkingControlPeriodTypeDomainEntityInput build() {
        return this.parkingControlPeriodTypeDomainEntityInput;
    }

}
