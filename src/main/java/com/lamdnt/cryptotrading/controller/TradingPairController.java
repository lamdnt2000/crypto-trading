package com.lamdnt.cryptotrading.controller;

import com.lamdnt.cryptotrading.mapper.TradingPairMapper;
import com.lamdnt.cryptotrading.model.TradingPairDTO;
import com.lamdnt.cryptotrading.service.TradingPairService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Trading Pair")
@RequestMapping("/api")
public class TradingPairController {
  private final TradingPairService tradingPairService;
  private final TradingPairMapper tradingPairMapper;

  @GetMapping("/market/trading-pairs")
  @Operation(summary = "Get all trading pairs", description = "Lists all available trading pairs")
  public List<TradingPairDTO> getTradingPairs() {
    return tradingPairService.getAll().stream().map(tradingPairMapper::toDTO).toList();
  }

  @GetMapping("/market/trading-pairs/{symbol}")
  @Operation(
      summary = "Get latest best price for symbol",
      description = "Fetches the latest aggregated price for a trading pair")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Latest price"),
  })
  public TradingPairDTO getTradingPair(@PathVariable("symbol") String symbol) {
    return tradingPairMapper.toDTO(tradingPairService.getBySymbol(symbol));
  }
}
