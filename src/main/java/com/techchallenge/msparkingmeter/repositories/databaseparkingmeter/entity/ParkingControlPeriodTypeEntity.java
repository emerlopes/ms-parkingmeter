package com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ParkingControlPeriodTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parking_control_period_id")
    private Long parkingControlPeriodId;

    @Column(name = "period_type")
    private PeriodTypeEnum periodType;

}
