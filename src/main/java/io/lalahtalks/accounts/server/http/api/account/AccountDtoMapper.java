package io.lalahtalks.accounts.server.http.api.account;

import io.lalahtalks.accounts.client.dto.AccountCreatedDto;
import io.lalahtalks.accounts.client.dto.AccountCreationRequestDto;
import io.lalahtalks.accounts.client.dto.AccountDto;
import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.account.Account;
import io.lalahtalks.accounts.server.domain.account.AccountCreated;
import io.lalahtalks.accounts.server.domain.account.AccountCreationRequest;
import io.lalahtalks.accounts.server.domain.user.Password;
import io.lalahtalks.accounts.server.domain.user.Username;
import org.springframework.stereotype.Component;

@Component
class AccountDtoMapper {

    AccountCreatedDto toDto(AccountCreated created) {
        return new AccountCreatedDto(
                created.accountId().value(),
                created.createdAt());
    }

    AccountDto toDto(Account account) {
        return new AccountDto(
                account.id().value(),
                account.email().value(),
                account.username().value(),
                account.createdAt());
    }

    AccountCreationRequest fromDto(AccountCreationRequestDto requestDto) {
        return new AccountCreationRequest(
                new Email(requestDto.email()),
                new Username(requestDto.username()),
                new Password(requestDto.password()));
    }

}
