package io.lalahtalks.accounts.server.domain.user;

import io.lalahtalks.accounts.server.domain.Email;

import java.time.Instant;

public record UserRegistrationRequest(
        Email email,
        Username username,
        Password password,
        Instant createdAt) {

}
