package io.lalahtalks.accounts.server.domain.account;

import java.time.Instant;

public record AccountCreated(
        AccountId accountId,
        Instant createdAt) {

    public static final class Builder {

        private AccountId accountId;
        private Instant createdAt;

        public Builder accountId(AccountId accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AccountCreated build() {
            return new AccountCreated(accountId, createdAt);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

}
