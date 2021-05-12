package io.lalahtalks.accounts.server.domain.account;

import io.lalahtalks.accounts.server.domain.Email;

import java.util.Optional;

public interface AccountRepository {

    boolean exists(Email email);

    Optional<Account> find(AccountId accountId);

    void save(Account account);

}
