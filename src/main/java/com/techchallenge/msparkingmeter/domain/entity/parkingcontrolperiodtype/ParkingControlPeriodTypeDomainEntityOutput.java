package com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import lombok.Data;

@Data
public class ParkingControlPeriodTypeDomainEntityOutput {

    @JsonProperty("parking_control_period_id")
    private Long parkingControlPeriodId;

    @JsonProperty("period_type")
    private PeriodTypeEnum periodType;
}
