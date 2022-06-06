package io.lalahtalks.accounts.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<AccountJpaDto, String> {

    boolean existsByEmail(String email);

}
