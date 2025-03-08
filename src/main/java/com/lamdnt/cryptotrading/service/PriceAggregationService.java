package com.lamdnt.cryptotrading.service;

import com.lamdnt.cryptotrading.connector.BinanceHttpClient;
import com.lamdnt.cryptotrading.connector.HuobiHttpClient;
import com.lamdnt.cryptotrading.entity.TradingPair;
import com.lamdnt.cryptotrading.model.BinanceTickerResponse;
import com.lamdnt.cryptotrading.model.HuobiTickerResponse;
import com.lamdnt.cryptotrading.utils.HttpHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PriceAggregationService {
    private final HuobiHttpClient huobiHttpClient;
    private final BinanceHttpClient binanceHttpClient;
    private final HttpHelper httpHelper;

    /**
     * Get the best ask and bid price for a list of symbols
     * @param symbols
     * @return A map of symbol to a pair of best ask and best bid price
     */
    public Map<String, Pair<BigDecimal, BigDecimal>> getAggregationPrice(List<String> symbols) {
        log.info("Fetching ticker price in binance");
        Map<String, BinanceTickerResponse> binancePrices = binanceHttpClient.getTickers(httpHelper.buildParam(symbols))
                .stream()
                .collect(Collectors.toMap(BinanceTickerResponse::getSymbol, ticker -> ticker));
        log.info("Fetched {} prices", binancePrices.size());

        log.info("Fetching ticker price in huobi");
        Map<String, HuobiTickerResponse.HuobiTicker> huobiPrices = huobiHttpClient.getTickers()
                .getData()
                .stream()
                .filter(ticker -> symbols.contains(ticker.getSymbol().toUpperCase()))
                .collect(Collectors.toMap(ticker -> ticker.getSymbol().toUpperCase(), ticker -> ticker));
        log.info("Fetched {} prices", huobiPrices.size());

        // Aggregate prices by selecting the best ask (min) and best bid (max)
        return symbols.stream()
                .map(symbol -> {
                    BinanceTickerResponse binance = binancePrices.get(symbol);
                    HuobiTickerResponse.HuobiTicker huobi = huobiPrices.get(symbol);

                    if (binance == null && huobi == null) {
                        return null;
                    }

                    BigDecimal bestAsk = minPrice(
                            Optional.ofNullable(binance).map(BinanceTickerResponse::getAskPrice).orElse(null),
                            Optional.ofNullable(huobi).map(HuobiTickerResponse.HuobiTicker::getAsk).orElse(null)
                    );

                    BigDecimal bestBid = maxPrice(
                            Optional.ofNullable(binance).map(BinanceTickerResponse::getBidPrice).orElse(null),
                            Optional.ofNullable(huobi).map(HuobiTickerResponse.HuobiTicker::getBid).orElse(null)
                    );

                    return Map.entry(symbol, Pair.of(bestAsk, bestBid));
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private BigDecimal minPrice(BigDecimal price1, BigDecimal price2) {
        return (price1 == null) ? price2 : (price2 == null) ? price1 : price1.min(price2);
    }

    private BigDecimal maxPrice(BigDecimal price1, BigDecimal price2) {
        return (price1 == null) ? price2 : (price2 == null) ? price1 : price1.max(price2);
    }
}
