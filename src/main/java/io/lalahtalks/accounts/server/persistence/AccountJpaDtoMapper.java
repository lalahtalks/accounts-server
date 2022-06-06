package io.lalahtalks.accounts.server.persistence;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.account.Account;
import io.lalahtalks.accounts.server.domain.account.AccountId;
import io.lalahtalks.accounts.server.domain.user.Username;
import org.springframework.stereotype.Component;

@Component
class AccountJpaDtoMapper {

    Account from(AccountJpaDto dto) {
        return new Account(
                new AccountId(dto.id()),
                new Email(dto.email()),
                new Username(dto.username()),
                dto.createdAt());
    }

    AccountJpaDto to(Account account) {
        return new AccountJpaDto(
                account.id().value(),
                account.email().value(),
                account.username().value(),
                account.createdAt());
    }

}
