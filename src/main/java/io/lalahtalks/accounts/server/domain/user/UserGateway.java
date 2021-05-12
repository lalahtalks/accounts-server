package io.lalahtalks.accounts.server.domain.user;

public interface UserGateway {

    UserRegistered register(UserRegistrationRequest request);

}
