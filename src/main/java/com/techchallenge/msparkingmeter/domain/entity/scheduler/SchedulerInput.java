package com.techchallenge.msparkingmeter.domain.entity.scheduler;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SchedulerInput {

    private UUID externalDriverId;
    private String phoneNumber;
    private LocalDateTime dateTimeNow;
    private Long durationInMinutes;
}
