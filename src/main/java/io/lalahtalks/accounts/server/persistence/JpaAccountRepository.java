package io.lalahtalks.accounts.server.persistence;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.account.Account;
import io.lalahtalks.accounts.server.domain.account.AccountId;
import io.lalahtalks.accounts.server.domain.account.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaAccountRepository implements AccountRepository {

    private final AccountDao accountDao;
    private final AccountJpaDtoMapper accountJpaDtoMapper;

    public JpaAccountRepository(AccountDao accountDao, AccountJpaDtoMapper accountJpaDtoMapper) {
        this.accountDao = accountDao;
        this.accountJpaDtoMapper = accountJpaDtoMapper;
    }

    @Override
    public boolean exists(Email email) {
        return accountDao.existsByEmail(email.value());
    }

    @Override
    public Optional<Account> find(AccountId accountId) {
        return accountDao.findById(accountId.value())
                .map(accountJpaDtoMapper::from);
    }

    @Override
    public void save(Account account) {
        var toBeSaved = accountJpaDtoMapper.to(account);
        accountDao.save(toBeSaved);
    }

}
