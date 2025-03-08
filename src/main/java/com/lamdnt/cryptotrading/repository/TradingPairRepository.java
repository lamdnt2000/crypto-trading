package com.lamdnt.cryptotrading.repository;

import com.lamdnt.cryptotrading.entity.TradingPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradingPairRepository extends JpaRepository<TradingPair, Long> {
    public Optional<TradingPair> findBySymbol(String symbol);
}
