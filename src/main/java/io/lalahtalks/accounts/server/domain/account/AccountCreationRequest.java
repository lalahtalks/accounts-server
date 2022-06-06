package io.lalahtalks.accounts.server.domain.account;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.user.Password;
import io.lalahtalks.accounts.server.domain.user.UserRegistrationRequest;
import io.lalahtalks.accounts.server.domain.user.Username;

import java.time.Instant;

public record AccountCreationRequest(
        Email email,
        Username username,
        Password password) {

    public UserRegistrationRequest toUserRegistrationRequest(Instant createdAt) {
        return new UserRegistrationRequest(
                email,
                username,
                password,
                createdAt);
    }

}
