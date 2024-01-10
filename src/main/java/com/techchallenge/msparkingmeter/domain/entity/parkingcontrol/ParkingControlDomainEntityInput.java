package com.techchallenge.msparkingmeter.domain.entity.parkingcontrol;

import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingControlDomainEntityInput {
    private LocalDateTime startTime;
    private Integer durationInMinutes;
    private ParkingControlPeriodTypeEntity periodType;
}
