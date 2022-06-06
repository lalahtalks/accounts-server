package io.lalahtalks.accounts.server.http.gateway;

import io.lalahtalks.accounts.server.domain.user.*;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.keycloak.admin.client.CreatedResponseUtil.getCreatedId;
import static org.keycloak.representations.idm.CredentialRepresentation.PASSWORD;

@Component
public class KeycloakUserGateway implements UserGateway {

    private final UsersResource usersResource;

    public KeycloakUserGateway(UsersResource usersResource) {
        this.usersResource = usersResource;
    }

    @Override
    public UserRegistered register(UserRegistrationRequest request) {
        var createdId = createAndGetId(request);
        return new UserRegistered(createdId, request.createdAt());
    }

    private UserId createAndGetId(UserRegistrationRequest request) {
        var credential = getCredential(request.password());
        var user = new UserRepresentation();
        user.setEnabled(true);
        user.setEmail(request.email().value());
        user.setUsername(request.username().value());
        user.setCredentials(List.of(credential));
        user.setEmailVerified(false);
        user.setCreatedTimestamp(request.createdAt().getEpochSecond());

        var response = usersResource.create(user);
        var createdId = getCreatedId(response);
        return new UserId(createdId);
    }

    private CredentialRepresentation getCredential(Password password) {
        var credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(PASSWORD);
        credential.setValue(password.value());
        return credential;
    }

}
