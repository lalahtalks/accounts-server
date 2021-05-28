package io.lalahtalks.accounts.server.domain.account;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.user.UserGateway;
import io.lalahtalks.accounts.server.domain.user.UserRegistered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final Clock clock;
    private final UserGateway userGateway;

    public Account get(AccountId accountId) {
        return accountRepository.find(accountId)
                .orElseThrow(AccountNotFoundException::new);
    }

    public AccountCreated create(AccountCreationRequest request) {
        checkAccountDoesNotAlreadyExist(request.getEmail());
        var userRegistered = registerUser(request);
        var account = create(userRegistered, request);
        return AccountCreated.builder()
                .accountId(account.getId())
                .createdAt(account.getCreatedAt())
                .build();
    }

    private Account create(UserRegistered userRegistered, AccountCreationRequest request) {
        var accountId = new AccountId(userRegistered.getUserId().getValue());
        var account = Account.builder()
                .id(accountId)
                .email(request.getEmail())
                .createdAt(userRegistered.getCreatedAt())
                .build();

        accountRepository.save(account);
        return account;
    }

    private UserRegistered registerUser(AccountCreationRequest request) {
        var now = clock.instant();
        var userCreationRequest = request.toUserRegistrationRequest(now);
        return userGateway.register(userCreationRequest);
    }

    private void checkAccountDoesNotAlreadyExist(Email email) {
        boolean exists = accountRepository.exists(email);
        if (exists) {
            throw new AccountAlreadyExistsException();
        }
    }

}
