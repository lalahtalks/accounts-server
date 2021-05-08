package io.lalahtalks.accounts.server.domain.user;

import io.lalahtalks.accounts.server.domain.Email;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class UserRegistrationRequest {

    @NonNull Email email;
    @NonNull Password password;

}
