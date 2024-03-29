package com.techchallenge.msparkingmeter.domain.entity.parkingcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techchallenge.msparkingmeter.repositories.databaseparkingmeter.entity.ParkingControlPeriodTypeEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ParkingControlDomainEntityOutput {

    @JsonProperty("parking_control_id")
    private Long parkingControlId;

    @JsonProperty("external_driver_id")
    private UUID externalDriverId;

    @JsonProperty("parking_start_time")
    private LocalDateTime parkingStartTime;

    @JsonProperty("parking_end_time")
    private LocalDateTime parkingEndTime;

    @JsonProperty("requested_minutes")
    private Integer requestedMinutes;

    @JsonProperty("real_minutes")
    private Integer realMinutes;

    @JsonProperty("predicted_value_to_be_paid")
    private BigDecimal predictedValueToBePaid;

    @JsonProperty("final_value_to_be_paid")
    private BigDecimal finalValueToBePaid;

    @JsonProperty("payment_receipt_id")
    private Long paymentReceiptId;

    @JsonProperty("payment_receipt")
    private PaymentReceipt paymentReceipt;

    @JsonProperty("period_type")
    private ParkingControlPeriodTypeEntity periodType;

}
