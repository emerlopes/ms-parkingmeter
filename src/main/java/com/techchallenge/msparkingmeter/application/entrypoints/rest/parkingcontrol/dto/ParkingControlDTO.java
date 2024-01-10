package com.techchallenge.msparkingmeter.application.entrypoints.rest.parkingcontrol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingControlDTO {

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("duration_in_minutes")
    private Integer durationInMinutes;

    @JsonProperty("period_type")
    private Long periodType;

}
