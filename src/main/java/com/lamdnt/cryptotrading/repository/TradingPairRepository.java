package com.lamdnt.cryptotrading.repository;

import com.lamdnt.cryptotrading.entity.TradingPair;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradingPairRepository extends JpaRepository<TradingPair, Long> {
  public Optional<TradingPair> findBySymbol(String symbol);
}
