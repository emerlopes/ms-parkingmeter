package com.techchallenge.msparkingmeter.repositories.hashicorp.dto;

import lombok.Data;

@Data
public class HashiCorpSecret {
    private String accountSid;
    private String authToken;
    private String messageServiceSid;
}
