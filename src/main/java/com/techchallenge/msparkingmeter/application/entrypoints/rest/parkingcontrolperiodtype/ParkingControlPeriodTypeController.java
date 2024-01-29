package com.techchallenge.msparkingmeter.application.entrypoints.rest.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.mappers.parkingcontrolperiodtype.ParkingControlPeriodTypeMapper;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteFindAllParkingControlPeriodTypeUseCase;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteFindParkingControlPeriodTypeByIdUseCase;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteSaveParkingControlPeriodTypeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável por gerenciar operações relacionadas aos tipos de período de controle de estacionamento.
 */
@RestController
@RequestMapping("/api/parking-control-period-type")
public class ParkingControlPeriodTypeController {

    private final IExecuteSaveParkingControlPeriodTypeUseCase executeSavePeriodTypeUseCase;
    private final IExecuteFindAllParkingControlPeriodTypeUseCase executeFindAllParkingControlPeriodTypeUseCase;
    private final IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase;

    /**
     * Construtor da classe ParkingControlPeriodTypeController.
     *
     * @param executeSavePeriodTypeUseCase               Caso de uso para salvar informações de tipos de período de controle de estacionamento.
     * @param executeFindAllParkingControlPeriodTypeUseCase Caso de uso para buscar todos os tipos de período de controle de estacionamento.
     * @param executeFindParkingControlPeriodTypeByIdUseCase Caso de uso para buscar informações de tipos de período de controle de estacionamento por ID.
     */
    public ParkingControlPeriodTypeController(IExecuteSaveParkingControlPeriodTypeUseCase executeSavePeriodTypeUseCase, IExecuteFindAllParkingControlPeriodTypeUseCase executeFindAllParkingControlPeriodTypeUseCase, IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase) {
        this.executeSavePeriodTypeUseCase = executeSavePeriodTypeUseCase;
        this.executeFindAllParkingControlPeriodTypeUseCase = executeFindAllParkingControlPeriodTypeUseCase;
        this.executeFindParkingControlPeriodTypeByIdUseCase = executeFindParkingControlPeriodTypeByIdUseCase;
    }

    /**
     * Endpoint para salvar informações de um tipo de período de controle de estacionamento.
     *
     * @param parkingControlPeriodTypeId ID do tipo de período de controle de estacionamento.
     * @return ResponseEntity com os detalhes da operação de salvar tipo de período de controle de estacionamento.
     */
    @PostMapping("/{parking-control-period-type-id}")
    public ResponseEntity<?> saveParkingControlPeriodType(
            @PathVariable("parking-control-period-type-id") Long parkingControlPeriodTypeId) {

        final var input = ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityInput(parkingControlPeriodTypeId);
        final var output = executeSavePeriodTypeUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

    /**
     * Endpoint para buscar todos os tipos de período de controle de estacionamento.
     *
     * @return ResponseEntity com a lista de tipos de período de controle de estacionamento encontrados.
     */
    @GetMapping
    public ResponseEntity<?> findAllParkingControlPeriodTypes() {
        final var output = executeFindAllParkingControlPeriodTypeUseCase.execute();
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

    /**
     * Endpoint para buscar informações de um tipo de período de controle de estacionamento por ID.
     *
     * @param parkingControlPeriodTypeId ID do tipo de período de controle de estacionamento.
     * @return ResponseEntity com os detalhes do tipo de período de controle de estacionamento encontrado.
     */
    @GetMapping("/{parking-control-period-type-id}")
    public ResponseEntity<?> findParkingControlPeriodTypeById(
            @PathVariable("parking-control-period-type-id") Long parkingControlPeriodTypeId) {

        final var input = ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityInput(parkingControlPeriodTypeId);
        final var output = executeFindParkingControlPeriodTypeByIdUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }
}
