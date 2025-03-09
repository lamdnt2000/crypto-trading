package com.lamdnt.cryptotrading.repository;

import com.lamdnt.cryptotrading.entity.CurrencyType;
import com.lamdnt.cryptotrading.entity.Wallet;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query(
      "SELECT w FROM Wallet w WHERE w.account.username = :username AND w.currency.name = :synbol")
  Optional<Wallet> findByAccountAndCurrency(
      @Param("username") String userName, @Param("synbol") CurrencyType symbol);
}
