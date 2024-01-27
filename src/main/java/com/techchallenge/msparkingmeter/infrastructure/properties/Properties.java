package com.techchallenge.msparkingmeter.infrastructure.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Properties {

    // APPLICATION
    @Value("${application.local.zone.sp}")
    private String localZoneSp;

    // HASHICORP
    @Value("${hashicorp.client.id}")
    private String hashicorpClientId;

    @Value("${hashicorp.client.secret}")
    private String hashicorpClientSecret;

}
