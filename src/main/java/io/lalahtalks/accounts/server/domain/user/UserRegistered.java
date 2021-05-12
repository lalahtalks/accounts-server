package io.lalahtalks.accounts.server.domain.user;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class UserRegistered {

    @NonNull UserId userId;
    @NonNull Instant createdAt;

}
