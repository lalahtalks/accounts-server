package io.lalahtalks.accounts.server.domain.account;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.user.Password;
import io.lalahtalks.accounts.server.domain.user.UserRegistrationRequest;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class AccountCreationRequest {

    @NonNull Email email;
    @NonNull Password password;

    public UserRegistrationRequest toUserRegistrationRequest() {
        return UserRegistrationRequest.builder()
                .email(email)
                .password(password)
                .build();
    }

}
