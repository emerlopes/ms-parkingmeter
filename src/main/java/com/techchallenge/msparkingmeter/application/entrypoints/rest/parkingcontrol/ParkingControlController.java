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

@RestController
@RequestMapping("/api/parking-control")
public class ParkingControlController {

    private final IExecuteSaveParkingControlUseCase executeSaveParkingControlUseCase;
    private final IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase;
    private final IExecuteUpdateParkingControlUseCase executeUpdateParkingControlUseCase;
    private final IExecuteFindParkingControlByIdUseCase executeFindParkingControlByIdUseCase;
    private final ExecuteCalculationFinalAmountToBePaid executeCalculationFinalAmountToBePaid;
    private final IDriversClient driversClient;

    public ParkingControlController(
            IExecuteSaveParkingControlUseCase executeSaveParkingControlUseCase,
            IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase, IExecuteUpdateParkingControlUseCase executeUpdateParkingControlUseCase, IExecuteFindParkingControlByIdUseCase executeFindParkingControlByIdUseCase, ExecuteCalculationFinalAmountToBePaid executeCalculationFinalAmountToBePaid, IDriversClient driversClient) {

        this.executeSaveParkingControlUseCase = executeSaveParkingControlUseCase;
        this.executeFindParkingControlPeriodTypeByIdUseCase = executeFindParkingControlPeriodTypeByIdUseCase;
        this.executeUpdateParkingControlUseCase = executeUpdateParkingControlUseCase;
        this.executeFindParkingControlByIdUseCase = executeFindParkingControlByIdUseCase;
        this.executeCalculationFinalAmountToBePaid = executeCalculationFinalAmountToBePaid;
        this.driversClient = driversClient;
    }

    @PostMapping("/{externalDriverId}")
    public ResponseEntity<?> saveParkingControl(
            @PathVariable UUID externalDriverId,
            @RequestHeader(value = "period-type-id") Long periodTypeId,
            @RequestHeader(value = "duration-in-minutes", required = false) Optional<Integer> durationInMinutes) {

        final var input = ParkingControlMapper.mapToParkingControlDomainEntityInput(externalDriverId, durationInMinutes);
        final var inputWithPeriodType = ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityInput(periodTypeId);

        final var parkingControlPeriodTypeOutput = executeFindParkingControlPeriodTypeByIdUseCase.execute(inputWithPeriodType);
        final var periodTypeEntity = ParkingControlPeriodTypeMapper.mapFromOutputToParkingControlPeriodTypeEntity(parkingControlPeriodTypeOutput.getData());

        input.setPeriodType(periodTypeEntity);

        final var output = executeSaveParkingControlUseCase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

    @PutMapping("/{parkingControlId}")
    public ResponseEntity<?> terminateParking(
            @PathVariable Long parkingControlId) {

        final var result = executeCalculationFinalAmountToBePaid.execute(parkingControlId);

//        final var parkingControl = executeFindParkingControlByIdUseCase.execute(parkingControlId);
//        final var parkingControlEntity = ParkingControlMapper.mapToParkingControlEntity(parkingControl.getData());
//
//        final var output = executeUpdateParkingControlUseCase.execute(parkingControlEntity);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
