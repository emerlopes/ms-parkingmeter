package com.techchallenge.msparkingmeter.application.entrypoints.rest.parkingcontrol;

import com.techchallenge.msparkingmeter.application.entrypoints.rest.parkingcontrol.dto.ParkingControlDTO;
import com.techchallenge.msparkingmeter.application.mappers.parkingcontrol.ParkingControlMapper;
import com.techchallenge.msparkingmeter.application.mappers.parkingcontrolperiodtype.ParkingControlPeriodTypeMapper;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrol.IExecuteSaveParkingControlUseCase;
import com.techchallenge.msparkingmeter.domain.usecase.parkingcontrolperiodtype.IExecuteFindParkingControlPeriodTypeByIdUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parking-control")
public class ParkingControlController {

    private final IExecuteSaveParkingControlUseCase executeSaveParkingControlUseCase;

    private final IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase;

    public ParkingControlController(IExecuteSaveParkingControlUseCase executeSaveParkingControlUseCase, IExecuteFindParkingControlPeriodTypeByIdUseCase executeFindParkingControlPeriodTypeByIdUseCase) {

        this.executeSaveParkingControlUseCase = executeSaveParkingControlUseCase;
        this.executeFindParkingControlPeriodTypeByIdUseCase = executeFindParkingControlPeriodTypeByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<?> saveParkingControl(@RequestBody ParkingControlDTO parkingControlDTO) {
        final var input = ParkingControlMapper.mapToParkingControlDomainEntityInput(parkingControlDTO);
        final var inputWithPeriodType = ParkingControlPeriodTypeMapper.mapToParkingControlPeriodTypeDomainEntityInput(parkingControlDTO.getPeriodType());
        final var parkingControlPeriodTypeOutput = executeFindParkingControlPeriodTypeByIdUseCase.execute(inputWithPeriodType);
        final var periodTypeEntity = ParkingControlPeriodTypeMapper.mapFromOutputToParkingControlPeriodTypeEntity(parkingControlPeriodTypeOutput.getData());
        input.setPeriodType(periodTypeEntity);

        final var output = executeSaveParkingControlUseCase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }
}
