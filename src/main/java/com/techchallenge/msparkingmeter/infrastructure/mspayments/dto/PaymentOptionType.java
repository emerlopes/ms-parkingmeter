package com.techchallenge.msparkingmeter.infrastructure.mspayments.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentOptionType {
    private Long paymentOptionTypeId;
    private PaymentOptionTypeEnum paymentOptionType;

}
