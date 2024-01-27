package com.techchallenge.msparkingmeter.repositories.hashicorp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenRequestDTO {
    private String audience;
    private String grant_type;
    private String client_id;
    private String client_secret;
}
