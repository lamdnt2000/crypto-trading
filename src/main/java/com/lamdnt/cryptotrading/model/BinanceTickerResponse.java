package com.lamdnt.cryptotrading.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BinanceTickerResponse implements Serializable {
    private String symbol;
    private BigDecimal bidPrice;
    private BigDecimal askPrice;
}
