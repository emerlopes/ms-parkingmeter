package com.techchallenge.msparkingmeter.domain.entity.parkingcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingControlDomainEntityOutput {

    @JsonProperty("parking_control_id")
    private Long parkingControlId;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("duration_in_minutes")
    private Integer durationInMinutes;

    @JsonProperty("period_type")
    private ParkingControlPeriodTypeEntity periodType;
}
