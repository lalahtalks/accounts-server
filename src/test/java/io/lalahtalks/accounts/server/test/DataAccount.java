package io.lalahtalks.accounts.server.test;

import io.lalahtalks.accounts.client.dto.AccountCreatedDto;
import io.lalahtalks.accounts.client.dto.AccountCreationRequestDto;
import io.lalahtalks.accounts.client.dto.AccountDto;
import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.account.Account;
import io.lalahtalks.accounts.server.domain.account.AccountId;
import io.lalahtalks.accounts.server.domain.user.Username;

import static io.lalahtalks.accounts.server.test.DataInstant.NOW;

public class DataAccount {

    public static final String ACCOUNT_1_ID_VALUE = "account_1";
    public static final AccountId ACCOUNT_1_ID = new AccountId(ACCOUNT_1_ID_VALUE);
    public static final String ACCOUNT_1_EMAIL_VALUE = "test@test.com";
    public static final Email ACCOUNT_1_EMAIL = new Email(ACCOUNT_1_EMAIL_VALUE);
    public static final String ACCOUNT_1_USERNAME_VALUE = "User 1";
    public static final Username ACCOUNT_1_USERNAME = new Username(ACCOUNT_1_USERNAME_VALUE);
    public static final String A_PASSWORD = "my_password";

    public static final Account ACCOUNT_1 = new Account(
            ACCOUNT_1_ID,
            ACCOUNT_1_EMAIL,
            ACCOUNT_1_USERNAME,
            NOW);

    public static final AccountDto ACCOUNT_1_DTO = new AccountDto(
            ACCOUNT_1_ID_VALUE,
            ACCOUNT_1_EMAIL_VALUE,
            ACCOUNT_1_USERNAME_VALUE,
            NOW);

    public static final AccountCreationRequestDto ACCOUNT_CREATION_REQUEST_DTO = new AccountCreationRequestDto(
            ACCOUNT_1_EMAIL_VALUE,
            ACCOUNT_1_USERNAME_VALUE,
            A_PASSWORD);

    public static final AccountCreatedDto ACCOUNT_CREATED_DTO = new AccountCreatedDto(ACCOUNT_1_ID_VALUE, NOW);

    private DataAccount() {

    }

}
