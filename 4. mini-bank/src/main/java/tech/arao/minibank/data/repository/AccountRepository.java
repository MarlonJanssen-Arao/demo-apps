package tech.arao.minibank.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.arao.minibank.data.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
