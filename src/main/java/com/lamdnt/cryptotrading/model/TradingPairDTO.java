package com.lamdnt.cryptotrading.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradingPairDTO implements Serializable {
  private Long id;
  private String symbol;
  private BigDecimal askPrice;
  private BigDecimal bidPrice;
}
