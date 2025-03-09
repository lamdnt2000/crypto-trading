package com.lamdnt.cryptotrading.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TradingPairDTO implements Serializable {
  private Long id;
  private String symbol;
  private BigDecimal askPrice;
  private BigDecimal bidPrice;
}
