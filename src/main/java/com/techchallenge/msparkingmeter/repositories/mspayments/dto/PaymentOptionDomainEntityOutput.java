package com.techchallenge.msparkingmeter.repositories.mspayments.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentOptionDomainEntityOutput {
    @JsonProperty("external_driver_id")
    private UUID externalDriverId;

    @JsonProperty("payment_option_type")
    private PaymentOptionType paymentOptionType;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
