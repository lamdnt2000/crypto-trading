package com.lamdnt.cryptotrading.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class HuobiTickerResponse {
    private List<HuobiTicker> data;

    @Data
    @NoArgsConstructor
    public static class HuobiTicker{
        private String symbol;
        private BigDecimal bid;
        private BigDecimal ask;
    }
}
