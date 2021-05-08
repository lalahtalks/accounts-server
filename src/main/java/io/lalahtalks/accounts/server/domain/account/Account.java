package io.lalahtalks.accounts.server.domain.account;

import io.lalahtalks.accounts.server.domain.Email;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class Account {

    @NonNull AccountId id;
    @NonNull Email email;
    @NonNull Instant createdAt;

}
