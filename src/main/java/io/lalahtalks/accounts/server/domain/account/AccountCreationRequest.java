package io.lalahtalks.accounts.server.domain.account;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.user.Password;
import io.lalahtalks.accounts.server.domain.user.UserRegistrationRequest;
import io.lalahtalks.accounts.server.domain.user.Username;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class AccountCreationRequest {

    @NonNull Email email;
    @NonNull Username username;
    @NonNull Password password;

    public UserRegistrationRequest toUserRegistrationRequest(Instant createdAt) {
        return UserRegistrationRequest.builder()
                .email(email)
                .username(username)
                .password(password)
                .createdAt(createdAt)
                .build();
    }

}
