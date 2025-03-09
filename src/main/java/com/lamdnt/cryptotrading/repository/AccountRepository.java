package com.lamdnt.cryptotrading.repository;

import com.lamdnt.cryptotrading.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  @Query("SELECT a FROM Account a LEFT JOIN FETCH a.wallets WHERE a.username = :username")
  Optional<Account> findByUsernameWithWallets(@Param("username") String username);

  Optional<Account> findByUsername(String username);
}
