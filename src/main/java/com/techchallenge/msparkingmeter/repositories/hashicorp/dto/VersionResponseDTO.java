package com.techchallenge.msparkingmeter.repositories.hashicorp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VersionResponseDTO {
    private String version;
    private String type;
    private String created_at;
    private String value;
}
