package com.techchallenge.msparkingmeter.repositories.mspayments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class ParkingPaymentReceiptDomainEntityOutput {

    @JsonProperty("receipt_number")
    private Long receiptNumber;

    @JsonProperty("payment_date")
    private LocalDateTime paymentDate;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("vehicle_plate_number")
    private String vehiclePlateNumber;

    @JsonProperty("payment_amount")
    private BigDecimal paymentAmount;

    @JsonProperty("payment_method")
    private String paymentMethod;
}
