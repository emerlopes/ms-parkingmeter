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

    @Column(name = "parking_start_time", nullable = false)
    private LocalDateTime parkingStartTime;

    @Column(name = "parking_end_time")
    private LocalDateTime parkingEndTime;

    @Column(name = "requested_minutes")
    private Integer requestedMinutes;

    @Column(name= "real_minutes")
    private Integer realMinutes;

    @Column(name = "predicted_value_to_be_paid")
    private BigDecimal predictedValueToBePaid;

    @Column(name = "final_value_to_be_paid")
    private BigDecimal finalValueToBePaid;

    @ManyToOne
    @JoinColumn(name = "parking_control_period_id")
    private ParkingControlPeriodTypeEntity periodType;
}

