package com.techchallenge.msparkingmeter.repositories.hashicorp;

import com.techchallenge.msparkingmeter.repositories.hashicorp.dto.TokenRequestDTO;
import com.techchallenge.msparkingmeter.repositories.hashicorp.dto.TokenResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "hashicorpauth", url = "https://auth.hashicorp.com/oauth/token")
public interface IHashicorpAuthClient {

    @PostMapping()
    TokenResponseDTO getAccessToken(TokenRequestDTO tokenRequestDTO);

}

