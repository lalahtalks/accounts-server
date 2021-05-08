package io.lalahtalks.accounts.server.domain.account;

import io.lalahtalks.accounts.server.domain.Email;

public interface AccountRepository {

    boolean exists(Email email);

    void save(Account account);

}
