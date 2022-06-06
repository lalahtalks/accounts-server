package io.lalahtalks.accounts.server.domain.account;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.user.UserGateway;
import io.lalahtalks.accounts.server.domain.user.UserRegistered;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class AccountService {

    private final AccountRepository accountRepository;
    private final Clock clock;
    private final UserGateway userGateway;

    public AccountService(AccountRepository accountRepository, Clock clock, UserGateway userGateway) {
        this.accountRepository = accountRepository;
        this.clock = clock;
        this.userGateway = userGateway;
    }

    public Account get(AccountId accountId) {
        return accountRepository.find(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    public AccountCreated create(AccountCreationRequest request) {
        checkAccountDoesNotAlreadyExist(request.email());
        var userRegistered = registerUser(request);
        var account = create(userRegistered, request);
        return new AccountCreated(account.id(), account.createdAt());
    }

    private Account create(UserRegistered userRegistered, AccountCreationRequest request) {
        var accountId = new AccountId(userRegistered.userId().value());
        var account = new Account(
                accountId,
                request.email(),
                request.username(),
                userRegistered.registeredAt());

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
            throw new AccountAlreadyExistsException(email);
        }
    }

}
