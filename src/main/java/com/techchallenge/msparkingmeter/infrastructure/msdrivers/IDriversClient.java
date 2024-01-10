package com.techchallenge.msparkingmeter.infrastructure.msdrivers;

import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.infrastructure.msdrivers.dto.DriverDomainEntityOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-drivers", url = "http://localhost:8080")
public interface IDriversClient {

    @GetMapping("/api/persons")
    CustomData<List<DriverDomainEntityOutput>> findAllDrivers();

}
