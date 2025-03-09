package com.lamdnt.cryptotrading.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HuobiTickerResponse {
  private List<HuobiTicker> data;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class HuobiTicker {
    private String symbol;
    private BigDecimal bid;
    private BigDecimal ask;
  }
}
