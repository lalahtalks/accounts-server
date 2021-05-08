package io.lalahtalks.accounts.server.http.gateway;

import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;

@Configuration
public class KeycloakConfig {

    private static final String REALM_LALAHTALKS = "lalahtalks";

    private final String serverUrl;
    private final String clientId;
    private final String clientSecret;

    public KeycloakConfig(
            @Value("${lalahtalks.auth-http-api.url}") String serverUrl,
            @Value("${lalahtalks.auth-http-api.client-id}") String clientId,
            @Value("${lalahtalks.auth-http-api.client-secret}") String clientSecret) {
        this.serverUrl = serverUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Bean
    public RealmResource lalahtalksRealm() {
        return KeycloakBuilder.builder()
                .grantType(CLIENT_CREDENTIALS)
                .serverUrl(serverUrl)
                .realm(REALM_LALAHTALKS)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build()
                .realm(REALM_LALAHTALKS);
    }

    @Bean
    public UsersResource usersResource(RealmResource lalahtalksRealm) {
        return lalahtalksRealm.users();
    }

}
