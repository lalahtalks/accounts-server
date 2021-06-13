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
                .accountId(created.getAccountId().getValue())
                .createdAt(created.getCreatedAt())
                .build();
    }

    AccountDto toDto(Account account) {
        return AccountDto.builder()
                .id(account.getId().getValue())
                .email(account.getEmail().getValue())
                .username(account.getUsername().getValue())
                .createdAt(account.getCreatedAt())
                .build();
    }

    AccountCreationRequest fromDto(AccountCreationRequestDto requestDto) {
        return AccountCreationRequest.builder()
                .email(new Email(requestDto.getEmail()))
                .username(new Username(requestDto.getUsername()))
                .password(new Password(requestDto.getPassword()))
                .build();
    }

}
