package io.lalahtalks.accounts.server.domain.account;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.user.Username;

import java.time.Instant;

public record Account(
        AccountId id,
        Email email,
        Username username,
        Instant createdAt) {

}
