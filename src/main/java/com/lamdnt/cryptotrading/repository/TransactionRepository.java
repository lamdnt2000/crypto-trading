package com.lamdnt.cryptotrading.repository;

import com.lamdnt.cryptotrading.entity.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  @Query("SELECT t FROM Transaction t WHERE t.account.username = :username")
  List<Transaction> findByUsername(@Param("username") String username);
}
