package io.lalahtalks.accounts.server.domain.user;

import java.time.Instant;

public record UserRegistered(UserId userId, Instant registeredAt) {

}
