package io.lalahtalks.accounts.server.persistence;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.account.Account;
import io.lalahtalks.accounts.server.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaAccountRepository implements AccountRepository {

    private final AccountEntityRepository accountEntityRepository;

    @Override
    public boolean exists(Email email) {
        return accountEntityRepository.existsByEmail(email.getValue());
    }

    @Override
    public void save(Account account) {
        var toBeSaved = toEntity(account);
        accountEntityRepository.save(toBeSaved);
    }

    private AccountEntity toEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId().getValue())
                .email(account.getEmail().getValue())
                .createdAt(account.getCreatedAt())
                .build();
    }

}
