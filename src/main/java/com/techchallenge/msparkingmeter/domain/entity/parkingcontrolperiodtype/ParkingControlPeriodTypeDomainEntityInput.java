package com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import lombok.Data;

@Data
public class ParkingControlPeriodTypeDomainEntityInput {

    private PeriodTypeEnum periodType;
}
