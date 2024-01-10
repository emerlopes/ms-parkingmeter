package com.techchallenge.msparkingmeter.application.entrypoints.rest.parkingcontrol;

import com.techchallenge.msparkingmeter.application.entrypoints.rest.parkingcontrol.dto.ParkingControlDTO;
import com.techchallenge.msparkingmeter.application.mappers.parkingcontrol.ParkingControlMapper;
import com.techchallenge.msparkingmeter.application.mappers.parkingcontrolperiodtype.ParkingControlPeriodTypeMapper;
import com.techchallenge.msparkingmeter.application.shared.dto.PeriodTypeEnum;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteSaveParkingControlUseCase;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteFindParkingControlPeriodTypeByIdUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/parking-control")
public class ParkingControlController {

    private final IExecuteSaveParkingControlUseCase executeSaveParkingControlUseCase;

    private final IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase;

    public ParkingControlController(IExecuteSaveParkingControlUseCase executeSaveParkingControlUseCase, IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase) {

        this.executeSaveParkingControlUseCase = executeSaveParkingControlUseCase;
        this.executeFindParkingControlPeriodTypeByIdUseCase = executeFindParkingControlPeriodTypeByIdUseCase;
    }

    @PostMapping("/{externalDriverId}/{periodTypeId}")
    public ResponseEntity<?> saveParkingControl(
            @PathVariable UUID externalDriverId,
            @PathVariable Long periodTypeId,
            @RequestParam(value = "duration_in_minutes", required = false) Optional<Integer> durationInMinutes) {

        final var input = ParkingControlMapper.mapToParkingControlDomainEntityInput(externalDriverId, durationInMinutes);
        final var inputWithPeriodType = ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityInput(periodTypeId);

        final var parkingControlPeriodTypeOutput = executeFindParkingControlPeriodTypeByIdUseCase.execute(inputWithPeriodType);
        final var periodTypeEntity = ParkingControlPeriodTypeMapper.mapFromOutputToParkingControlPeriodTypeEntity(parkingControlPeriodTypeOutput.getData());

        input.setPeriodType(periodTypeEntity);

        final var output = executeSaveParkingControlUseCase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }
}
