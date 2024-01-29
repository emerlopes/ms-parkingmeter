package com.techchallenge.msparkingmeter.application.entrypoints.rest.parkingcontrol;

import com.techchallenge.msparkingmeter.application.mappers.parkingcontrol.ParkingControlMapper;
import com.techchallenge.msparkingmeter.application.mappers.parkingcontrolperiodtype.ParkingControlPeriodTypeMapper;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.ExecuteCalculationFinalAmountToBePaid;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteFindParkingControlByIdUseCase;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteSaveParkingControlUseCase;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteUpdateParkingControlUseCase;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteFindParkingControlPeriodTypeByIdUseCase;
import com.techchallenge.msparkingmeter.repositories.msdrivers.IDriversClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Controlador responsável por gerenciar operações relacionadas ao controle de estacionamento.
 */
@RestController
@RequestMapping("/api/parking-control")
public class ParkingControlController {

    private final IExecuteSaveParkingControlUseCase executeSaveParkingControlUseCase;
    private final IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase;
    private final ExecuteCalculationFinalAmountToBePaid executeCalculationFinalAmountToBePaid;

    /**
     * Construtor da classe ParkingControlController.
     *
     * @param executeSaveParkingControlUseCase               Caso de uso para salvar informações de controle de estacionamento.
     * @param executeFindParkingControlPeriodTypeByIdUseCase Caso de uso para buscar informações de tipos de período de controle de estacionamento por ID.
     * @param executeCalculationFinalAmountToBePaid          Caso de uso para calcular o valor final a ser pago pelo estacionamento.
     */
    public ParkingControlController(
            IExecuteSaveParkingControlUseCase executeSaveParkingControlUseCase,
            IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase,
            ExecuteCalculationFinalAmountToBePaid executeCalculationFinalAmountToBePaid) {

        this.executeSaveParkingControlUseCase = executeSaveParkingControlUseCase;
        this.executeFindParkingControlPeriodTypeByIdUseCase = executeFindParkingControlPeriodTypeByIdUseCase;
        this.executeCalculationFinalAmountToBePaid = executeCalculationFinalAmountToBePaid;
    }

    /**
     * Endpoint para salvar informações de controle de estacionamento.
     *
     * @param externalDriverId  ID externo do motorista.
     * @param periodTypeId      ID do tipo de período de controle.
     * @param durationInMinutes Duração em minutos (opcional) do estacionamento.
     * @return ResponseEntity com os detalhes da operação de salvar controle de estacionamento.
     */
    @PostMapping("/{externalDriverId}")
    public ResponseEntity<?> saveParkingControl(
            @PathVariable UUID externalDriverId,
            @RequestParam(value = "period-type-id") Long periodTypeId,
            @RequestParam(value = "requested-minutes", required = false) Optional<Integer> durationInMinutes) {

        final var input = ParkingControlMapper.mapToParkingControlDomainEntityInput(externalDriverId, durationInMinutes);
        final var inputWithPeriodType = ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityInput(periodTypeId);

        final var parkingControlPeriodTypeOutput = executeFindParkingControlPeriodTypeByIdUseCase.execute(inputWithPeriodType);
        final var periodTypeEntity = ParkingControlPeriodTypeMapper.mapFromOutputToParkingControlPeriodTypeEntity(parkingControlPeriodTypeOutput.getData());

        input.setPeriodType(periodTypeEntity);

        final var output = executeSaveParkingControlUseCase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

    /**
     * Endpoint para encerrar um controle de estacionamento e calcular o valor final a ser pago.
     *
     * @param parkingControlId ID do controle de estacionamento a ser encerrado.
     * @return ResponseEntity com os detalhes do cálculo do valor final a ser pago.
     */
    @PutMapping("/{parkingControlId}")
    public ResponseEntity<?> terminateParking(
            @PathVariable Long parkingControlId) {

        final var result = executeCalculationFinalAmountToBePaid.execute(parkingControlId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
