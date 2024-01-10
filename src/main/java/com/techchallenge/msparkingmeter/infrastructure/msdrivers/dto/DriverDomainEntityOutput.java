package com.techchallenge.msparkingmeter.infrastructure.msdrivers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverDomainEntityOutput {

    @JsonProperty("external_id")
    private UUID externalId;

    private String name;
    private int age;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
