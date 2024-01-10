package com.techchallenge.msparkingmeter.infrastructure.msdrivers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverDomainEntityOutput {

    private UUID externalId;

    private String name;
    private int age;

    private LocalDateTime createdAt;
}
