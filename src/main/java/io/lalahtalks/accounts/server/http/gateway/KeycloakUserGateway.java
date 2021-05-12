package io.lalahtalks.accounts.server.http.gateway;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.user.*;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

import static org.keycloak.admin.client.CreatedResponseUtil.getCreatedId;
import static org.keycloak.representations.idm.CredentialRepresentation.PASSWORD;

@Component
@RequiredArgsConstructor
public class KeycloakUserGateway implements UserGateway {

    private final UsersResource usersResource;

    @Override
    public UserRegistered register(UserRegistrationRequest request) {
        var credential = getCredential(request.getPassword());
        var createdId = createAndGetId(request.getEmail(), credential, request.getCreatedAt());
        return UserRegistered.builder()
                .userId(createdId)
                .createdAt(request.getCreatedAt())
                .build();
    }

    private CredentialRepresentation getCredential(Password password) {
        var credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(PASSWORD);
        credential.setValue(password.getValue());
        return credential;
    }

    private UserId createAndGetId(Email email, CredentialRepresentation credential, Instant createdAt) {
        var user = new UserRepresentation();
        user.setEnabled(true);
        user.setEmail(email.getValue());
        user.setUsername(email.getValue());
        user.setCredentials(List.of(credential));
        user.setEmailVerified(false);
        user.setCreatedTimestamp(createdAt.getEpochSecond());

        var response = usersResource.create(user);
        var createdId = getCreatedId(response);
        return new UserId(createdId);
    }

}
