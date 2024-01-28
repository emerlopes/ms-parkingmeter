package com.techchallenge.msparkingmeter.repositories.mspayments.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingPaymentReceiptDomainEntityOutput {

    @JsonProperty("receipt_number")
    private Long receiptNumber;

    @JsonProperty("payment_date")
    private LocalDateTime paymentDate;

    @JsonProperty("payment_amount")
    private BigDecimal paymentAmount;

    @JsonProperty("payment_method")
    private String paymentMethod;
}
