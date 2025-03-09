package com.lamdnt.cryptotrading.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HuobiTickerResponse {
  private List<HuobiTicker> data;

  @Data
  @NoArgsConstructor
  public static class HuobiTicker {
    private String symbol;
    private BigDecimal bid;
    private BigDecimal ask;
  }
}
