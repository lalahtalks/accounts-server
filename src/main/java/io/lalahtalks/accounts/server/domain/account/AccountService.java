package io.lalahtalks.accounts.server.domain.account;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.user.UserGateway;
import io.lalahtalks.accounts.server.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final Clock clock;
    private final UserGateway userGateway;

    public AccountCreated create(AccountCreationRequest request) {
        checkAccountDoesNotAlreadyExist(request.getEmail());
        var userId = registerUser(request);
        var account = create(userId, request);
        return AccountCreated.builder()
                .accountId(account.getId())
                .createdAt(account.getCreatedAt())
                .build();
    }

    private Account create(UserId userId, AccountCreationRequest request) {
        var now = clock.instant();
        var accountId = new AccountId(userId.getValue());
        var account = Account.builder()
                .id(accountId)
                .email(request.getEmail())
                .createdAt(now)
                .build();

        accountRepository.save(account);
        return account;
    }

    private UserId registerUser(AccountCreationRequest request) {
        var userCreationRequest = request.toUserRegistrationRequest();
        return userGateway.register(userCreationRequest);
    }

    private void checkAccountDoesNotAlreadyExist(Email email) {
        boolean exists = accountRepository.exists(email);
        if (exists) {
            throw new AccountAlreadyExistsException();
        }
    }

}
