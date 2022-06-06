package io.lalahtalks.accounts.server.domain.account;

import java.time.Instant;

public record AccountCreated(AccountId accountId, Instant createdAt) {

}
