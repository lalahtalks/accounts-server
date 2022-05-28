package io.lalahtalks.accounts.server.domain.user;

import io.lalahtalks.accounts.server.domain.Email;

import java.time.Instant;

public record UserRegistrationRequest(
        Email email,
        Username username,
        Password password,
        Instant createdAt) {

    public static final class Builder {

        private Email email;
        private Username username;
        private Password password;
        private Instant createdAt;

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

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserRegistrationRequest build() {
            return new UserRegistrationRequest(email, username, password, createdAt);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

}
