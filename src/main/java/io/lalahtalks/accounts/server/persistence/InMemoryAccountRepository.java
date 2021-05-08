package io.lalahtalks.accounts.server.persistence;

import io.lalahtalks.accounts.server.domain.Email;
import io.lalahtalks.accounts.server.domain.account.Account;
import io.lalahtalks.accounts.server.domain.account.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Component
public class InMemoryAccountRepository implements AccountRepository {

    private final Set<Account> accounts = new TreeSet<>(Comparator.comparing(it -> it.getEmail().getValue()));

    @Override
    public boolean exists(Email email) {
        return accounts.stream()
                .anyMatch(it -> email.equals(it.getEmail()));
    }

    @Override
    public void save(Account account) {
        accounts.add(account);
    }

}
