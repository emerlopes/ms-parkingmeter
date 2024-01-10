package com.techchallenge.msparkingmeter.application.entrypoints.rest.parkingcontrolperiodtype;

import com.techchallenge.msparkingmeter.application.mappers.parkingcontrolperiodtype.ParkingControlPeriodTypeMapper;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteSavePeriodTypeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parking-control-period-type")
public class ParkingControlPeriodTypeController {

    private final IExecuteSavePeriodTypeUseCase executeSavePeriodTypeUseCase;

    public ParkingControlPeriodTypeController(IExecuteSavePeriodTypeUseCase executeSavePeriodTypeUseCase) {
        this.executeSavePeriodTypeUseCase = executeSavePeriodTypeUseCase;
    }

    @PostMapping("/{parking-control-period-type-id}")
    public ResponseEntity<?> saveParkingControlPeriodType(
            @PathVariable("parking-control-period-type-id") Long parkingControlPeriodTypeId) {

        final var input = ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityInput(parkingControlPeriodTypeId);
        final var output = executeSavePeriodTypeUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }
}
