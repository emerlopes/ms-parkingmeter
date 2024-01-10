package com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ParkingControlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parking_control_id")
    private Long parkingControlId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    private Integer durationInMinutes;

    @ManyToOne
    @JoinColumn(name = "parking_control_period_id")
    private ParkingControlPeriodTypeEntity periodType;
}
