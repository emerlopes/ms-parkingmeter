package com.techchallenge.msparkingmeter.repositories.msparkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.mappers.parkingcontrolperiodtype.ParkingControlPeriodTypeMapper;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityInput;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrolperiodtype.IParkingControlPeriodTypeDomainService;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.repository.IParkingControlPeriodTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação do serviço de domínio para tipos de períodos de controle de estacionamento.
 */
@Service
public class ParkingControlPeriodTypeDomainServiceImpl implements IParkingControlPeriodTypeDomainService {

    private final IParkingControlPeriodTypeRepository parkingControlPeriodTypeRepository;

    /**
     * Construtor da classe.
     *
     * @param parkingControlPeriodTypeRepository O repositório de entidades de tipos de períodos de controle de estacionamento.
     */
    public ParkingControlPeriodTypeDomainServiceImpl(IParkingControlPeriodTypeRepository parkingControlPeriodTypeRepository) {
        this.parkingControlPeriodTypeRepository = parkingControlPeriodTypeRepository;
    }

    /**
     * Salva um novo tipo de período de controle de estacionamento com base nos dados de entrada.
     *
     * @param input Os dados de entrada do tipo de período de controle de estacionamento.
     * @return Os dados de saída do tipo de período de controle de estacionamento após a operação de salvamento.
     */
    @Override
    public ParkingControlPeriodTypeDomainEntityOutput saveParkingControlPeriodType(ParkingControlPeriodTypeDomainEntityInput input) {
        final var entity = ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeEntity(input);
        final var entitySaved = parkingControlPeriodTypeRepository.save(entity);

        return ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityOutput(entitySaved);
    }

    /**
     * Recupera todos os tipos de períodos de controle de estacionamento.
     *
     * @return Uma lista de dados de saída dos tipos de períodos de controle de estacionamento encontrados.
     */
    @Override
    public List<ParkingControlPeriodTypeDomainEntityOutput> findAllParkingControlPeriodType() {
        final var entities = parkingControlPeriodTypeRepository.findAll();

        return ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntitiesOutput(entities);
    }

    /**
     * Encontra um tipo de período de controle de estacionamento com base no ID especificado.
     *
     * @param input Os dados de entrada contendo o ID do tipo de período de controle de estacionamento a ser encontrado.
     * @return Os dados de saída do tipo de período de controle de estacionamento encontrado.
     */
    @Override
    public ParkingControlPeriodTypeDomainEntityOutput findParkingControlPeriodTypeById(ParkingControlPeriodTypeDomainEntityInput input) {
        final var entity = parkingControlPeriodTypeRepository.findById(input.getPeriodType().getValue());
        return ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityOutput(entity.get());
    }
}

