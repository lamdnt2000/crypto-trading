package com.lamdnt.cryptotrading.service;

import com.lamdnt.cryptotrading.entity.TradingPair;
import com.lamdnt.cryptotrading.repository.TradingPairRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TradingPairService {
    private final TradingPairRepository tradingPairRepository;

    public List<TradingPair> getAll(){
        return tradingPairRepository.findAll();
    }

    public void update(TradingPair tradingPair) {
        tradingPairRepository.save(tradingPair);
    }
}
