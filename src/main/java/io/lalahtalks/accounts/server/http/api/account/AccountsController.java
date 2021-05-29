package io.lalahtalks.accounts.server.http.api.account;

import io.lalahtalks.accounts.client.dto.AccountCreatedDto;
import io.lalahtalks.accounts.client.dto.AccountCreationRequestDto;
import io.lalahtalks.accounts.client.dto.AccountDto;
import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.account.*;
import io.lalahtalks.accounts.server.domain.user.Password;
import io.lalahtalks.accounts.server.domain.user.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;

import static io.lalahtalks.accounts.client.http.contract.AccountsHttpPaths.*;
import static io.lalahtalks.accounts.client.http.contract.AccountsProblemType.ACCOUNT_ALREADY_EXISTS;
import static io.lalahtalks.accounts.client.http.contract.AccountsProblemType.ACCOUNT_NOT_FOUND;

@RestController
@RequestMapping(ACCOUNTS_PATH)
@RequiredArgsConstructor
public class AccountsController {

    private final AccountService accountService;

    @GetMapping(path = ACCOUNT_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    AccountDto get(@PathVariable(ACCOUNT_ID) String accountIdValue) {
        var accountId = new AccountId(accountIdValue);
        var account = accountService.get(accountId);
        return toDto(account);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    AccountCreatedDto create(@RequestBody AccountCreationRequestDto requestDto) {
        var request = fromDto(requestDto);
        var created = accountService.create(request);
        return toDto(created);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Problem handleAccountNotFoundException() {
        return ACCOUNT_NOT_FOUND.toProblem();
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    Problem handleAccountAlreadyExistsException() {
        return ACCOUNT_ALREADY_EXISTS.toProblem();
    }

    private AccountCreatedDto toDto(AccountCreated created) {
        return AccountCreatedDto.builder()
                .accountId(created.getAccountId().getValue())
                .createdAt(created.getCreatedAt())
                .build();
    }

    private AccountDto toDto(Account account) {
        return AccountDto.builder()
                .id(account.getId().getValue())
                .email(account.getEmail().getValue())
                .username(account.getUsername().getValue())
                .createdAt(account.getCreatedAt())
                .build();
    }

    private AccountCreationRequest fromDto(AccountCreationRequestDto requestDto) {
        return AccountCreationRequest.builder()
                .email(new Email(requestDto.getEmail()))
                .username(new Username(requestDto.getUsername()))
                .password(new Password(requestDto.getPassword()))
                .build();
    }

}
