package com.techchallenge.msparkingmeter.repositories.mspayments.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ParkingPaymentReceiptDomainEntityInput {
    private LocalDateTime paymentDate;
    private String customerName;
    private String vehiclePlateNumber;
    private BigDecimal paymentAmount;
    private String paymentMethod;
}
