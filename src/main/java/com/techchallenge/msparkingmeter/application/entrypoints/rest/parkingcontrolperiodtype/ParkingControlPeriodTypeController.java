package com.techchallenge.msparkingmeter.application.entrypoints.rest.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.mappers.parkingcontrolperiodtype.ParkingControlPeriodTypeMapper;
import com.techchallenge.msparkingmeter.domain.entity.parkingcontrolperiodtype.ParkingControlPeriodTypeDomainEntityOutput;
import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteFindAllParkingControlPeriodTypeUseCase;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteFindParkingControlPeriodTypeByIdUseCase;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteSaveParkingControlPeriodTypeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável por gerenciar operações relacionadas aos tipos de período de controle de estacionamento.
 */
@RestController
@RequestMapping("/api/parking-control-period-type")
@Tag(name = "Tipos de Período de Controle de Estacionamento", description = "API para Gerenciamento de Tipos de Período de Controle de Estacionamento")
public class ParkingControlPeriodTypeController {

    private final IExecuteSaveParkingControlPeriodTypeUseCase executeSavePeriodTypeUseCase;
    private final IExecuteFindAllParkingControlPeriodTypeUseCase executeFindAllParkingControlPeriodTypeUseCase;
    private final IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase;

    /**
     * Construtor da classe ParkingControlPeriodTypeController.
     *
     * @param executeSavePeriodTypeUseCase                   Caso de uso para salvar informações de tipos de período de controle de estacionamento.
     * @param executeFindAllParkingControlPeriodTypeUseCase  Caso de uso para buscar todos os tipos de período de controle de estacionamento.
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
    @Operation(
            summary = "Salvar informações de um tipo de período de controle de estacionamento",
            description = "Salva informações de um tipo de período de controle de estacionamento com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação bem-sucedida, informações de tipo de período de controle de estacionamento salvas com sucesso.")
    })
    @PostMapping("/{parking-control-period-type-id}")
    public ResponseEntity<CustomData<ParkingControlPeriodTypeDomainEntityOutput>> saveParkingControlPeriodType(
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
    @Operation(
            summary = "Buscar todos os tipos de período de controle de estacionamento",
            description = "Busca todos os tipos de período de controle de estacionamento disponíveis."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida, lista de tipos de período de controle de estacionamento encontrada.")
    })
    @GetMapping
    public ResponseEntity<CustomData<List<ParkingControlPeriodTypeDomainEntityOutput>>> findAllParkingControlPeriodTypes() {
        final var output = executeFindAllParkingControlPeriodTypeUseCase.execute();
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

    /**
     * Endpoint para buscar informações de um tipo de período de controle de estacionamento por ID.
     *
     * @param parkingControlPeriodTypeId ID do tipo de período de controle de estacionamento.
     * @return ResponseEntity com os detalhes do tipo de período de controle de estacionamento encontrado.
     */
    @Operation(
            summary = "Buscar informações de um tipo de período de controle de estacionamento por ID",
            description = "Busca informações de um tipo de período de controle de estacionamento com base no ID fornecido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida, informações de tipo de período de controle de estacionamento encontradas.")
    })
    @GetMapping("/{parking-control-period-type-id}")
    public ResponseEntity<CustomData<ParkingControlPeriodTypeDomainEntityOutput>> findParkingControlPeriodTypeById(
            @PathVariable("parking-control-period-type-id") Long parkingControlPeriodTypeId) {

        final var input = ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityInput(parkingControlPeriodTypeId);
        final var output = executeFindParkingControlPeriodTypeByIdUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }
}
