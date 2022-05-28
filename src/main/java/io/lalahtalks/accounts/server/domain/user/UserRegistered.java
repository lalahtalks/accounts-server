package io.lalahtalks.accounts.server.domain.user;

import java.time.Instant;

public record UserRegistered(
        UserId userId,
        Instant registeredAt) {

    public static final class Builder {

        private UserId userId;
        private Instant registeredAt;

        public Builder userId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public Builder registeredAt(Instant registeredAt) {
            this.registeredAt = registeredAt;
            return this;
        }

        public UserRegistered build() {
            return new UserRegistered(userId, registeredAt);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

}
