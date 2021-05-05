package io.lalahtalks.accounts.server.http.api.account;

import io.lalahtalks.accounts.client.dto.AccountCreatedDto;
import io.lalahtalks.accounts.client.dto.AccountCreationRequestDto;
import io.lalahtalks.accounts.server.domain.AccountCreated;
import io.lalahtalks.accounts.server.domain.AccountCreationRequest;
import io.lalahtalks.accounts.server.domain.AccountService;
import io.lalahtalks.accounts.server.domain.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private final AccountService accountService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    AccountCreatedDto create(@RequestBody AccountCreationRequestDto requestDto) {
        var request = fromDto(requestDto);
        var created = accountService.create(request);
        return toDto(created);
    }

    private AccountCreatedDto toDto(AccountCreated created) {
        return AccountCreatedDto.builder()
                .accountId(created.getAccountId().getValue())
                .createdAt(created.getCreatedAt())
                .build();
    }

    private AccountCreationRequest fromDto(AccountCreationRequestDto requestDto) {
        return AccountCreationRequest.builder()
                .email(new Email(requestDto.email))
                .build();
    }

}
