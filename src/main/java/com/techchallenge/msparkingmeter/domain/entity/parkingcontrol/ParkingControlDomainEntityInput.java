package com.techchallenge.msparkingmeter.domain.entity.parkingcontrol;

import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ParkingControlDomainEntityInput {
    private UUID externalDriverId;
    private LocalDateTime startTime;
    private Integer durationInMinutes;
    private ParkingControlPeriodTypeEntity periodType;
}
