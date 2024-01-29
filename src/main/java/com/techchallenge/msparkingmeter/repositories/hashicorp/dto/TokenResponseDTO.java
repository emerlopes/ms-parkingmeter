package com.techchallenge.msparkingmeter.repositories.hashicorp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponseDTO {
    private String access_token;
    private long expires_in;
    private String token_type;
}

