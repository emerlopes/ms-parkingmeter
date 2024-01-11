package com.techchallenge.msparkingmeter.domain.entity.parkingcontrol;

import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.infrastructure.msdrivers.dto.DriverDomainEntityOutput;
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
    private DriverDomainEntityOutput driver;
}
