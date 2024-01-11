package com.techchallenge.msparkingmeter.repositories.msparkingcontrol;

import com.techchallenge.msparkingmeter.application.mappers.parkingcontrol.ParkingControlMapper;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrol.IParkingControlDomainService;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.repository.IParkingControlEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingControlDomainServiceImpl implements IParkingControlDomainService {

    private final IParkingControlEntityRepository ParkingControlRepository;

    public ParkingControlDomainServiceImpl(IParkingControlEntityRepository parkingControlRepository) {
        ParkingControlRepository = parkingControlRepository;
    }

    @Override
    public ParkingControlDomainEntityOutput saveParkingControl(ParkingControlDomainEntityInput input) {
        final var entity = ParkingControlMapper.mapToParkingControlEntity(input);
        final var entitySaved = ParkingControlRepository.save(entity);

        return ParkingControlMapper.mapToParkingControlDomainEntityOutput(entitySaved);
    }
}
