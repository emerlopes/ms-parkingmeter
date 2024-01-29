package com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.impl;

import com.techchallenge.msparkingmeter.domain.entity.parkingcontrol.ParkingControlDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.sevice.parkingcontrol.IParkingControlDomainService;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteFindParkingControlByIdUseCase;
import org.springframework.stereotype.Service;

/**
 * Implementação do caso de uso para encontrar informações de controle de estacionamento por ID.
 */
@Service
public class ExecuteFindParkingControlByIdUseCaseImpl implements IExecuteFindParkingControlByIdUseCase {

    private final IParkingControlDomainService parkingControlDomainService;

    /**
     * Construtor da classe ExecuteFindParkingControlByIdUseCaseImpl.
     *
     * @param parkingControlDomainService Serviço de domínio para controle de estacionamento.
     */
    public ExecuteFindParkingControlByIdUseCaseImpl(IParkingControlDomainService parkingControlDomainService) {
        this.parkingControlDomainService = parkingControlDomainService;
    }

    /**
     * Executa o caso de uso para encontrar informações de controle de estacionamento por ID.
     *
     * @param domainObject ID do controle de estacionamento a ser encontrado.
     * @return CustomData contendo informações sobre o controle de estacionamento encontrado.
     */
    @Override
    public CustomData<ParkingControlDomainEntityOutput> execute(Long domainObject) {
        final var output = parkingControlDomainService.findParkingControlById(domainObject);

        CustomData<ParkingControlDomainEntityOutput> customData = new CustomData<>();
        customData.setData(output);
        return customData;
    }
}
