package io.lalahtalks.accounts.server.http.gateway;

import io.lalahtalks.accounts.server.domain.user.Password;
import io.lalahtalks.accounts.server.domain.user.UserGateway;
import io.lalahtalks.accounts.server.domain.user.UserId;
import io.lalahtalks.accounts.server.domain.user.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import static org.keycloak.admin.client.CreatedResponseUtil.getCreatedId;
import static org.keycloak.representations.idm.CredentialRepresentation.PASSWORD;

@Component
@RequiredArgsConstructor
public class KeycloakUserGateway implements UserGateway {

    private final UsersResource usersResource;

    @Override
    public UserId register(UserRegistrationRequest request) {
        var createdId = createAndGetId(request);
        setPassword(createdId, request.getPassword());
        return new UserId(createdId);
    }

    private void setPassword(String createdId, Password password) {
        var credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(PASSWORD);
        credential.setValue(password.getValue());

        usersResource.get(createdId)
                .resetPassword(credential);
    }

    private String createAndGetId(UserRegistrationRequest request) {
        var user = new UserRepresentation();
        user.setEnabled(true);
        user.setEmail(request.getEmail().getValue());
        user.setUsername(request.getEmail().getValue());

        var response = usersResource.create(user);
        return getCreatedId(response);
    }

}
