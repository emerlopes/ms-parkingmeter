package com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.repository;

import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParkingControlEntityRepository extends JpaRepository<ParkingControlEntity, Long> {
}
