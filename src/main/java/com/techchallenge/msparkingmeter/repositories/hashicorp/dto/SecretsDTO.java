package com.techchallenge.msparkingmeter.repositories.hashicorp.dto;

import lombok.Data;

import java.util.List;

@Data
public class SecretsDTO {

    private List<SecretResponseDTO> secrets;
}
