package com.techchallenge.msparkingmeter.repositories.service.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.mappers.parkingcontrolperiodtype.ParkingControlPeriodTypeMapper;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrolperiodtype.IParkingControlPeriodTypeDomainService;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.repository.IParkingControlPeriodTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingControlPeriodTypeDomainServiceImpl implements IParkingControlPeriodTypeDomainService {

    private final IParkingControlPeriodTypeRepository parkingControlPeriodTypeRepository;

    public ParkingControlPeriodTypeDomainServiceImpl(IParkingControlPeriodTypeRepository parkingControlPeriodTypeRepository) {
        this.parkingControlPeriodTypeRepository = parkingControlPeriodTypeRepository;
    }

    @Override
    public ParkingControlPeriodTypeDomainEntityOutput saveParkingControlPeriodType(ParkingControlPeriodTypeDomainEntityInput input) {
        final var entity = ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeEntity(input);
        final var entitySaved = parkingControlPeriodTypeRepository.save(entity);
        return ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityOutput(entitySaved);
    }
}
