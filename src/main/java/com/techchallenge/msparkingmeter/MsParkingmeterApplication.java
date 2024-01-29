package com.techchallenge.msparkingmeter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients({"com.techchallenge.msparkingmeter.infrastructure.*", "com.techchallenge.msparkingmeter.repositories.*"})
public class MsParkingmeterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsParkingmeterApplication.class, args);
    }

}
