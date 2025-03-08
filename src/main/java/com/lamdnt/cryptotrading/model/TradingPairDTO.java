package com.lamdnt.cryptotrading.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TradingPairDTO implements Serializable {
    private Long id;
    private String symbol;
    private BigDecimal askPrice;
    private BigDecimal bidPrice;
}
