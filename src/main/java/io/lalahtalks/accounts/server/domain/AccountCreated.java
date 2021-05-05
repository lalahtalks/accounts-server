package io.lalahtalks.accounts.server.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class AccountCreated {

    @NonNull AccountId accountId;
    @NonNull Instant createdAt;

}
