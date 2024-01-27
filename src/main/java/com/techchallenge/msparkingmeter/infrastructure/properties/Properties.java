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

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.messageServiceSid}")
    private String messageServiceSid;

}