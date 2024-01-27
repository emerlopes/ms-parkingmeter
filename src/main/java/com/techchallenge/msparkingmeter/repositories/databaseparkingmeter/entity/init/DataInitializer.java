package com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.init;

import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.repository.IParkingControlPeriodTypeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer {

    private final IParkingControlPeriodTypeRepository repository;

    public DataInitializer(IParkingControlPeriodTypeRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            for (PeriodTypeEnum type : PeriodTypeEnum.values()) {
                ParkingControlPeriodTypeEntity entity = new ParkingControlPeriodTypeEntity();
                entity.setPeriodType(type);
                repository.save(entity);
            }
        }
    }
}
