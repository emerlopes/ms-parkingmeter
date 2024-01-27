package com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb04_parking_control")
public class ParkingControlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parking_control_id", nullable = false)
    private Long parkingControlId;

    @NotNull
    @Column(name = "external_driver_id")
    private UUID externalDriverId;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "duration_in_minutes")
    private Integer durationInMinutes;

    @Column(name = "value_to_be_paid")
    private BigDecimal valueToBePaid;

    @ManyToOne
    @JoinColumn(name = "parking_control_period_id")
    private ParkingControlPeriodTypeEntity periodType;
}

