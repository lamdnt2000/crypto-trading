package com.lamdnt.cryptotrading.service;

import com.lamdnt.cryptotrading.entity.TradingPair;
import com.lamdnt.cryptotrading.repository.TradingPairRepository;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TradingPairService {
  private final TradingPairRepository tradingPairRepository;

  public List<TradingPair> getAll() {
    return tradingPairRepository.findAll();
  }

  public TradingPair getBySymbol(String symbol) {
    return tradingPairRepository.findBySymbol(symbol).orElseThrow(() -> new RuntimeException("Trading pair not found"));
  }

  public void update(TradingPair tradingPair) {
    tradingPairRepository.save(tradingPair);
  }
}
