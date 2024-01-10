package com.techchallenge.msparkingmeter.application.entrypoints.rest.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.mappers.parkingcontrolperiodtype.ParkingControlPeriodTypeMapper;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteFindAllParkingControlPeriodTypeUseCase;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteFindParkingControlPeriodTypeByIdUseCase;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteSaveParkingControlPeriodTypeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking-control-period-type")
public class ParkingControlPeriodTypeController {

    private final IExecuteSaveParkingControlPeriodTypeUseCase executeSavePeriodTypeUseCase;
    private final IExecuteFindAllParkingControlPeriodTypeUseCase executeFindAllParkingControlPeriodTypeUseCase;
    private final IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase;

    public ParkingControlPeriodTypeController(IExecuteSaveParkingControlPeriodTypeUseCase executeSavePeriodTypeUseCase, IExecuteFindAllParkingControlPeriodTypeUseCase executeFindAllParkingControlPeriodTypeUseCase, IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase) {
        this.executeSavePeriodTypeUseCase = executeSavePeriodTypeUseCase;
        this.executeFindAllParkingControlPeriodTypeUseCase = executeFindAllParkingControlPeriodTypeUseCase;
        this.executeFindParkingControlPeriodTypeByIdUseCase = executeFindParkingControlPeriodTypeByIdUseCase;
    }

    @PostMapping("/{parking-control-period-type-id}")
    public ResponseEntity<?> saveParkingControlPeriodType(
            @PathVariable("parking-control-period-type-id") Long parkingControlPeriodTypeId) {

        final var input = ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityInput(parkingControlPeriodTypeId);
        final var output = executeSavePeriodTypeUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

    @GetMapping
    public ResponseEntity<?> findAllParkingControlPeriodTypes() {
        final var output = executeFindAllParkingControlPeriodTypeUseCase.execute();
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

    @GetMapping("/{parking-control-period-type-id}")
    public ResponseEntity<?> findParkingControlPeriodTypeById(
            @PathVariable("parking-control-period-type-id") Long parkingControlPeriodTypeId) {

        final var input = ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityInput(parkingControlPeriodTypeId);
        final var output = executeFindParkingControlPeriodTypeByIdUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }
}
