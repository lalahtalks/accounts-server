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
        return AccountCreatedDto.builder()
                .accountId(created.accountId().value())
                .createdAt(created.createdAt())
                .build();
    }

    AccountDto toDto(Account account) {
        return AccountDto.builder()
                .id(account.id().value())
                .email(account.email().value())
                .username(account.username().value())
                .createdAt(account.createdAt())
                .build();
    }

    AccountCreationRequest fromDto(AccountCreationRequestDto requestDto) {
        return AccountCreationRequest.builder()
                .email(new Email(requestDto.email()))
                .username(new Username(requestDto.username()))
                .password(new Password(requestDto.password()))
                .build();
    }

}
