package com.techchallenge.msparkingmeter.repositories.hashicorp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecretResponseDTO {
    private String name;
    private VersionResponseDTO version;
    private String created_at;
    private String latest_version;
}
