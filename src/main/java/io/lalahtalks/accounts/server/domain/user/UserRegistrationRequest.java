package io.lalahtalks.accounts.server.domain.user;

import io.lalahtalks.accounts.server.domain.Email;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class UserRegistrationRequest {

    @NonNull Email email;
    @NonNull Username username;
    @NonNull Password password;
    @NonNull Instant createdAt;

}
