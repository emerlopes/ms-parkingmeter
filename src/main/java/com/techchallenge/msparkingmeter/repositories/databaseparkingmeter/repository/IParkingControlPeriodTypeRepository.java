package com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.repository;

import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParkingControlPeriodTypeRepository extends JpaRepository<ParkingControlPeriodTypeEntity, Long> {
}
