package io.lalahtalks.accounts.server.persistence;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.account.Account;
import io.lalahtalks.accounts.server.domain.account.AccountId;
import io.lalahtalks.accounts.server.domain.account.AccountRepository;
import io.lalahtalks.accounts.server.domain.user.Username;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaAccountRepository implements AccountRepository {

    private final AccountEntityRepository accountEntityRepository;

    public JpaAccountRepository(AccountEntityRepository accountEntityRepository) {
        this.accountEntityRepository = accountEntityRepository;
    }

    @Override
    public boolean exists(Email email) {
        return accountEntityRepository.existsByEmail(email.value());
    }

    @Override
    public Optional<Account> find(AccountId accountId) {
        return accountEntityRepository.findById(accountId.value())
                .map(this::fromEntity);
    }

    @Override
    public void save(Account account) {
        var toBeSaved = toEntity(account);
        accountEntityRepository.save(toBeSaved);
    }

    private Account fromEntity(AccountEntity entity) {
        return Account.builder()
                .id(new AccountId(entity.id()))
                .email(new Email(entity.email()))
                .username(new Username(entity.username()))
                .createdAt(entity.createdAt())
                .build();
    }

    private AccountEntity toEntity(Account account) {
        return AccountEntity.builder()
                .id(account.id().value())
                .email(account.email().value())
                .username(account.username().value())
                .createdAt(account.createdAt())
                .build();
    }

}
