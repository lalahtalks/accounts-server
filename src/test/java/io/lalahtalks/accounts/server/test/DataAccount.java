package io.lalahtalks.accounts.server.test;

import io.lalahtalks.accounts.client.dto.AccountCreatedDto;
import io.lalahtalks.accounts.client.dto.AccountCreationRequestDto;
import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.account.Account;
import io.lalahtalks.accounts.server.domain.account.AccountId;
import lombok.NoArgsConstructor;

import static io.lalahtalks.accounts.server.test.DataInstant.NOW;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DataAccount {

    public static final String ACCOUNT_1_ID_VALUE = "account_1";
    public static final AccountId ACCOUNT_1_ID = new AccountId(ACCOUNT_1_ID_VALUE);
    public static final String ACCOUNT_1_EMAIL_VALUE = "test@test.com";
    public static final Email ACCOUNT_1_EMAIL = new Email(ACCOUNT_1_EMAIL_VALUE);
    public static final String A_PASSWORD = "my_password";

    public static final Account ACCOUNT_1 = Account.builder()
            .id(ACCOUNT_1_ID)
            .email(ACCOUNT_1_EMAIL)
            .createdAt(NOW)
            .build();

    public static final AccountCreationRequestDto ACCOUNT_CREATION_REQUEST_1_DTO = AccountCreationRequestDto.builder()
            .email(ACCOUNT_1_EMAIL_VALUE)
            .password(A_PASSWORD)
            .build();

    public static final AccountCreationRequestDto ACCOUNT_CREATION_REQUEST_2_DTO = AccountCreationRequestDto.builder()
            .email("already_exists@test.com")
            .password(A_PASSWORD)
            .build();

    public static final AccountCreatedDto ACCOUNT_CREATED_DTO = AccountCreatedDto.builder()
            .accountId(ACCOUNT_1_ID_VALUE)
            .createdAt(NOW)
            .build();

}
