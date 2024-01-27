package com.techchallenge.msparkingmeter.repositories.hashicorp;

import com.techchallenge.msparkingmeter.infrastructure.properties.Properties;
import com.techchallenge.msparkingmeter.repositories.hashicorp.dto.HashiCorpSecret;
import com.techchallenge.msparkingmeter.repositories.hashicorp.dto.SecretResponseDTO;
import com.techchallenge.msparkingmeter.repositories.hashicorp.dto.TokenRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HashicorpClientService {

    private final Properties properties;

    private final IHashicorpAuthClient iHashicorpAuthClient;
    private final IHashicorpSecretClient iHashicorpSecretClient;

    public HashicorpClientService(Properties properties, IHashicorpAuthClient iHashicorpAuthClient, IHashicorpSecretClient iHashicorpSecretClient) {
        this.properties = properties;
        this.iHashicorpAuthClient = iHashicorpAuthClient;
        this.iHashicorpSecretClient = iHashicorpSecretClient;
    }

    private String getAccessToken() {

        final var tokenRequestDTO = new TokenRequestDTO();
        tokenRequestDTO.setAudience("https://api.hashicorp.cloud");
        tokenRequestDTO.setGrant_type("client_credentials");
        tokenRequestDTO.setClient_id(properties.getHashicorpClientId());
        tokenRequestDTO.setClient_secret(properties.getHashicorpClientSecret());

        return iHashicorpAuthClient.getAccessToken(tokenRequestDTO).getAccess_token();
    }

    private Optional<String> getSecretValue(List<SecretResponseDTO> secrets, String secretName) {

        return secrets.stream()
                .filter(secret -> secret.getName().equals(secretName))
                .findFirst()
                .map(secret -> secret.getVersion().getValue());
    }

    public HashiCorpSecret getHashiCorpSecret() {
        final var token = getAccessToken();
        final var authorization = "Bearer " + token;
        final var secrets = iHashicorpSecretClient.getHashiCorpSecrets(authorization).getSecrets();

        final var hashiCorpSecret = new HashiCorpSecret();

        hashiCorpSecret.setAccountSid(getSecretValue(secrets, "accountSid").orElse(null));
        hashiCorpSecret.setAuthToken(getSecretValue(secrets, "authToken").orElse(null));
        hashiCorpSecret.setMessageServiceSid(getSecretValue(secrets, "messageServiceSid").orElse(null));

        return hashiCorpSecret;
    }

}
