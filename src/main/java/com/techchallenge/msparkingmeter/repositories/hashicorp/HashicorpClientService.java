package com.techchallenge.msparkingmeter.repositories.hashicorp;

import com.techchallenge.msparkingmeter.infrastructure.properties.Properties;
import com.techchallenge.msparkingmeter.repositories.hashicorp.dto.HashiCorpSecret;
import com.techchallenge.msparkingmeter.repositories.hashicorp.dto.SecretResponseDTO;
import com.techchallenge.msparkingmeter.repositories.hashicorp.dto.TokenRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço para acessar o HashiCorp Vault e obter segredos.
 */
@Service
public class HashicorpClientService {

    private final Properties properties;

    private final IHashicorpAuthClient iHashicorpAuthClient;
    private final IHashicorpSecretClient iHashicorpSecretClient;

    /**
     * Construtor da classe.
     *
     * @param properties           As propriedades de configuração do HashiCorp.
     * @param iHashicorpAuthClient O cliente de autenticação do HashiCorp.
     * @param iHashicorpSecretClient O cliente de segredos do HashiCorp.
     */
    public HashicorpClientService(Properties properties, IHashicorpAuthClient iHashicorpAuthClient, IHashicorpSecretClient iHashicorpSecretClient) {
        this.properties = properties;
        this.iHashicorpAuthClient = iHashicorpAuthClient;
        this.iHashicorpSecretClient = iHashicorpSecretClient;
    }

    /**
     * Obtém o token de acesso ao HashiCorp Vault.
     *
     * @return O token de acesso.
     */
    private String getAccessToken() {

        final var tokenRequestDTO = new TokenRequestDTO();
        tokenRequestDTO.setAudience("https://api.hashicorp.cloud");
        tokenRequestDTO.setGrant_type("client_credentials");
        tokenRequestDTO.setClient_id(properties.getHashicorpClientId());
        tokenRequestDTO.setClient_secret(properties.getHashicorpClientSecret());

        return iHashicorpAuthClient.getAccessToken(tokenRequestDTO).getAccess_token();
    }

    /**
     * Obtém o valor de um segredo específico com base no nome.
     *
     * @param secrets    A lista de segredos disponíveis.
     * @param secretName O nome do segredo a ser obtido.
     * @return O valor do segredo se encontrado, ou um valor vazio se não encontrado.
     */
    private Optional<String> getSecretValue(List<SecretResponseDTO> secrets, String secretName) {

        return secrets.stream()
                .filter(secret -> secret.getName().equals(secretName))
                .findFirst()
                .map(secret -> secret.getVersion().getValue());
    }

    /**
     * Obtém os segredos do HashiCorp Vault.
     *
     * @return Os segredos obtidos do HashiCorp Vault.
     */
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

