package com.techchallenge.msparkingmeter.repositories.msparkingcontrol;

import com.techchallenge.msparkingmeter.application.mappers.parkingcontrol.ParkingControlMapper;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrol.IParkingControlDomainService;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.repository.IParkingControlEntityRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * Implementação do serviço de domínio para controle de estacionamento.
 */
@Service
public class ParkingControlDomainServiceImpl implements IParkingControlDomainService {

    private final IParkingControlEntityRepository ParkingControlRepository;

    /**
     * Construtor da classe.
     *
     * @param parkingControlRepository O repositório de entidades de controle de estacionamento.
     */
    public ParkingControlDomainServiceImpl(IParkingControlEntityRepository parkingControlRepository) {
        ParkingControlRepository = parkingControlRepository;
    }

    /**
     * Salva um novo controle de estacionamento com base nos dados de entrada.
     *
     * @param input Os dados de entrada do controle de estacionamento.
     * @return Os dados de saída do controle de estacionamento após a operação de salvamento.
     */
    @Override
    public ParkingControlDomainEntityOutput saveParkingControl(ParkingControlDomainEntityInput input) {
        final var entity = ParkingControlMapper.mapToParkingControlEntity(input);
        final var entitySaved = ParkingControlRepository.save(entity);

        return ParkingControlMapper.mapToParkingControlDomainEntityOutput(entitySaved);
    }

    /**
     * Salva um controle de estacionamento com base nos dados de entrada de saída.
     *
     * @param input Os dados de entrada e saída do controle de estacionamento.
     * @return Os dados de saída do controle de estacionamento após a operação de salvamento.
     */
    @Override
    public ParkingControlDomainEntityOutput saveParkingControl(ParkingControlDomainEntityOutput input) {
        final var entity = ParkingControlMapper.mapToParkingControlEntity(input);
        final var entitySaved = ParkingControlRepository.save(entity);

        return ParkingControlMapper.mapToParkingControlDomainEntityOutput(entitySaved);
    }

    /**
     * Encontra um controle de estacionamento com base no ID especificado.
     *
     * @param id O ID do controle de estacionamento a ser encontrado.
     * @return Os dados de saída do controle de estacionamento encontrado.
     */
    @Override
    public ParkingControlDomainEntityOutput findParkingControlById(Long id) {
        final var entity = ParkingControlRepository.findById(id).orElseThrow();

        return ParkingControlMapper.mapToParkingControlDomainEntityOutput(entity);
    }
}

