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
        return UserRegistrationRequest.builder()
                .email(email)
                .username(username)
                .password(password)
                .createdAt(createdAt)
                .build();
    }

    public static final class Builder {

        private Email email;
        private Username username;
        private Password password;

        public Builder email(Email email) {
            this.email = email;
            return this;
        }

        public Builder username(Username username) {
            this.username = username;
            return this;
        }

        public Builder password(Password password) {
            this.password = password;
            return this;
        }

        public AccountCreationRequest build() {
            return new AccountCreationRequest(email, username, password);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

}
