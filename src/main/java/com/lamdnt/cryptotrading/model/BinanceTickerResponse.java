package com.lamdnt.cryptotrading.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BinanceTickerResponse implements Serializable {
  private String symbol;
  private BigDecimal bidPrice;
  private BigDecimal askPrice;
}
