package com.lamdnt.cryptotrading.controller;

import com.lamdnt.cryptotrading.mapper.TradingPairMapper;
import com.lamdnt.cryptotrading.model.TradingPairDTO;
import com.lamdnt.cryptotrading.service.TradingPairService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TradingPairController {
    private final TradingPairService tradingPairService;
    private final TradingPairMapper tradingPairMapper;

    @GetMapping("/market/trading-pairs")
    public List<TradingPairDTO> getTradingPairs() {
        return tradingPairService.getAll().stream().map(tradingPairMapper::toDTO).toList();
    }
}
