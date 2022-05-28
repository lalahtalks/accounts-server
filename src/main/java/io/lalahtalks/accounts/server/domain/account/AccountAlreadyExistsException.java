package io.lalahtalks.accounts.server.domain.account;

import io.lalahtalks.accounts.server.domain.Email;

public final class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(Email email) {
        super("email '" + email.value() + "'");
    }

}
