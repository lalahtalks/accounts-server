package io.lalahtalks.accounts.server.http.api.account;

import io.lalahtalks.accounts.client.dto.AccountCreatedDto;
import io.lalahtalks.accounts.client.dto.AccountCreationRequestDto;
import io.lalahtalks.accounts.client.dto.AccountDto;
import io.lalahtalks.accounts.client.http.contract.problem.AccountAlreadyExistsProblem;
import io.lalahtalks.accounts.client.http.contract.problem.AccountNotFoundProblem;
import io.lalahtalks.accounts.server.domain.account.AccountAlreadyExistsException;
import io.lalahtalks.accounts.server.domain.account.AccountId;
import io.lalahtalks.accounts.server.domain.account.AccountNotFoundException;
import io.lalahtalks.accounts.server.domain.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static io.lalahtalks.accounts.client.http.contract.AccountsHttpPaths.*;

@RestController
@RequestMapping(ACCOUNTS_PATH)
public class AccountsController {

    private final AccountDtoMapper accountDtoMapper;
    private final AccountService accountService;

    public AccountsController(AccountDtoMapper accountDtoMapper, AccountService accountService) {
        this.accountDtoMapper = accountDtoMapper;
        this.accountService = accountService;
    }

    @GetMapping(path = ACCOUNT_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    AccountDto get(@PathVariable(ACCOUNT_ID) AccountId accountId) {
        var account = accountService.get(accountId);
        return accountDtoMapper.toDto(account);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    AccountCreatedDto create(@RequestBody AccountCreationRequestDto requestDto) {
        var request = accountDtoMapper.fromDto(requestDto);
        var created = accountService.create(request);
        return accountDtoMapper.toDto(created);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    AccountNotFoundProblem handle(AccountNotFoundException e) {
        return new AccountNotFoundProblem(e.getMessage());
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    AccountAlreadyExistsProblem handle(AccountAlreadyExistsException e) {
        return new AccountAlreadyExistsProblem(e.getMessage());
    }

}
