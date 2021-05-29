package io.lalahtalks.accounts.server.persistence;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.account.Account;
import io.lalahtalks.accounts.server.domain.account.AccountId;
import io.lalahtalks.accounts.server.domain.account.AccountRepository;
import io.lalahtalks.accounts.server.domain.user.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaAccountRepository implements AccountRepository {

    private final AccountEntityRepository accountEntityRepository;

    @Override
    public boolean exists(Email email) {
        return accountEntityRepository.existsByEmail(email.getValue());
    }

    @Override
    public Optional<Account> find(AccountId accountId) {
        return accountEntityRepository.findById(accountId.getValue())
                .map(this::fromEntity);
    }

    @Override
    public void save(Account account) {
        var toBeSaved = toEntity(account);
        accountEntityRepository.save(toBeSaved);
    }

    private Account fromEntity(AccountEntity entity) {
        return Account.builder()
                .id(new AccountId(entity.getId()))
                .email(new Email(entity.getEmail()))
                .username(new Username(entity.getUsername()))
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private AccountEntity toEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId().getValue())
                .email(account.getEmail().getValue())
                .username(account.getUsername().getValue())
                .createdAt(account.getCreatedAt())
                .build();
    }

}
