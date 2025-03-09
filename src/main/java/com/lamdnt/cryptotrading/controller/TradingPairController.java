package com.lamdnt.cryptotrading.controller;

import com.lamdnt.cryptotrading.mapper.TradingPairMapper;
import com.lamdnt.cryptotrading.model.TradingPairDTO;
import com.lamdnt.cryptotrading.service.TradingPairService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TradingPairController {
  private final TradingPairService tradingPairService;
  private final TradingPairMapper tradingPairMapper;

  @GetMapping("/market/trading-pairs")
  public List<TradingPairDTO> getTradingPairs() {
    return tradingPairService.getAll().stream().map(tradingPairMapper::toDTO).toList();
  }

  @GetMapping("/market/trading-pairs/{id}")
  public TradingPairDTO getTradingPair(@PathVariable("id") Long id) {
    return tradingPairMapper.toDTO(tradingPairService.getById(id));
  }
}
