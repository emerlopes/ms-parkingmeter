package com.techchallenge.msparkingmeter.application.entrypoints.rest.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.mappers.parkingcontrolperiodtype.ParkingControlPeriodTypeMapper;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteFindAllParkingControlPeriodTypeUseCase;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteSavePeriodTypeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking-control-period-type")
public class ParkingControlPeriodTypeController {

    private final IExecuteSavePeriodTypeUseCase executeSavePeriodTypeUseCase;
    private final IExecuteFindAllParkingControlPeriodTypeUseCase executeFindAllParkingControlPeriodTypeUseCase;

    public ParkingControlPeriodTypeController(IExecuteSavePeriodTypeUseCase executeSavePeriodTypeUseCase, IExecuteFindAllParkingControlPeriodTypeUseCase executeFindAllParkingControlPeriodTypeUseCase) {
        this.executeSavePeriodTypeUseCase = executeSavePeriodTypeUseCase;
        this.executeFindAllParkingControlPeriodTypeUseCase = executeFindAllParkingControlPeriodTypeUseCase;
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
}
