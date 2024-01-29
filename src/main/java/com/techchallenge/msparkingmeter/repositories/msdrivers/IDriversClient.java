package com.techchallenge.msparkingmeter.repositories.msdrivers;

import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.repositories.msdrivers.dto.DriverDomainEntityOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "ms-drivers", url = "http://localhost:8080")
public interface IDriversClient {

    @GetMapping("/api/drivers/{externalDriverId}")
    CustomData<DriverDomainEntityOutput> findDriverById(@PathVariable("externalDriverId") UUID externalDriverId);

}

