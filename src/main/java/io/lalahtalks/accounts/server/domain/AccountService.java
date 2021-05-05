package io.lalahtalks.accounts.server.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
@RequiredArgsConstructor
public class AccountService {

    private final Clock clock;

    public AccountCreated create(AccountCreationRequest request) {
        return AccountCreated.builder()
                .accountId(new AccountId("account_1"))
                .createdAt(clock.instant())
                .build();
    }

}
