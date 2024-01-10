package com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb04_parking_control")
public class ParkingControlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parking_control_id")
    private Long parkingControlId;

    @Column(name = "external_driver_id")
    private UUID externalDriverId;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;

    @ManyToOne
    @JoinColumn(name = "parking_control_period_id")
    private ParkingControlPeriodTypeEntity periodType;
}
