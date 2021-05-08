package io.lalahtalks.accounts.server.domain.user;

public interface UserGateway {

    UserId register(UserRegistrationRequest request);

}
