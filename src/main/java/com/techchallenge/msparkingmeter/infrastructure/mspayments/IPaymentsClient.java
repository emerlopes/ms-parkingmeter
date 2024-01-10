package com.techchallenge.msparkingmeter.infrastructure.mspayments;

import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.infrastructure.msdrivers.dto.DriverDomainEntityOutput;
import com.techchallenge.msparkingmeter.infrastructure.mspayments.dto.PaymentOptionDomainEntityOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "ms-payments", url = "http://localhost:8081")
public interface IPaymentsClient {

    @GetMapping("/api/payment-options/{externalDriverId}")
    CustomData<PaymentOptionDomainEntityOutput> findPaymentOptionByExternalDriverId(@PathVariable("externalDriverId") UUID externalDriverId);

}

