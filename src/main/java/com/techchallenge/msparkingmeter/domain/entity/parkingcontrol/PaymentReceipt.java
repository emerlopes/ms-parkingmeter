package com.techchallenge.msparkingmeter.domain.entity.parkingcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Builder
public class PaymentReceipt {
    @JsonProperty("parking_start_time")
    private LocalDateTime parkingStartTime;

    @JsonProperty("parking_end_time")
    private LocalDateTime parkingEndTime;

    @JsonProperty("parking_minutes")
    private int parkingMinutes;

    @JsonProperty("parking_tariff")
    private BigDecimal parkingTariff;

    @JsonProperty("total_payment_amount")
    private BigDecimal totalPaymentAmount;
}
