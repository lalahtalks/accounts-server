package io.lalahtalks.accounts.server.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class AccountCreationRequest {

    @NonNull Email email;

}
