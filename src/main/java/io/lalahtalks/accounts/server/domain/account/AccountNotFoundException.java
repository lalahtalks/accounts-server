package io.lalahtalks.accounts.server.domain.account;

public final class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(AccountId accountId) {
        super("account ID '" + accountId.value() + "'");
    }

}
