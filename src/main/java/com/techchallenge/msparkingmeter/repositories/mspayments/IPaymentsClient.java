package com.techchallenge.msparkingmeter.repositories.mspayments;

import com.techchallenge.msparkingmeter.domain.shared.CustomData;
import com.techchallenge.msparkingmeter.repositories.mspayments.dto.ParkingPaymentReceiptDomainEntityInput;
import com.techchallenge.msparkingmeter.repositories.mspayments.dto.ParkingPaymentReceiptDomainEntityOutput;
import com.techchallenge.msparkingmeter.repositories.mspayments.dto.PaymentOptionDomainEntityOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(name = "ms-payments", url = "http://localhost:8081")
public interface IPaymentsClient {

    @GetMapping("/api/payment-options/{externalDriverId}")
    CustomData<PaymentOptionDomainEntityOutput> findPaymentOptionByExternalDriverId(@PathVariable("externalDriverId") UUID externalDriverId);

    @PostMapping("/api/payment-receipts")
    CustomData<ParkingPaymentReceiptDomainEntityOutput> savePaymentReceipt(ParkingPaymentReceiptDomainEntityInput input);


}

