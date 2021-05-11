package io.lalahtalks.accounts.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, String> {

    boolean existsByEmail(String email);

}
