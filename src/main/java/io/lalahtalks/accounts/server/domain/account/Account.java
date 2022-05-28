package io.lalahtalks.accounts.server.domain.account;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.user.Username;

import java.time.Instant;

public record Account(
        AccountId id,
        Email email,
        Username username,
        Instant createdAt) {

    public static final class Builder {

        private AccountId id;
        private Email email;
        private Username username;
        private Instant createdAt;

        public Builder id(AccountId id) {
            this.id = id;
            return this;
        }

        public Builder email(Email email) {
            this.email = email;
            return this;
        }

        public Builder username(Username username) {
            this.username = username;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Account build() {
            return new Account(id, email, username, createdAt);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

}
