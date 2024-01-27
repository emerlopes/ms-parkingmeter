package com.techchallenge.msparkingmeter.repositories.hashicorp;

import com.techchallenge.msparkingmeter.repositories.hashicorp.dto.SecretsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "hashicorpsecret",
        url = "https://api.cloud.hashicorp.com/secrets/2023-06-13/organizations/ad7ff889-2857-4ad6-bf64-421ac9c564ca/projects/344b28b8-e564-4c67-8954-e533dd689efe/apps/parkingmeter/open")
public interface IHashicorpSecretClient {

    @GetMapping()
    SecretsDTO getHashiCorpSecrets(@RequestHeader("Authorization") String authorizationHeader);

}

