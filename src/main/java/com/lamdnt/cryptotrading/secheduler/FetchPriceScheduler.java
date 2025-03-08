package com.lamdnt.cryptotrading.secheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamdnt.cryptotrading.connector.BinanceHttpClient;
import com.lamdnt.cryptotrading.utils.HttpHelper;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FetchPriceScheduler {
    private final BinanceHttpClient binanceHttpClient;
    private final HttpHelper httpHelper;

    @Scheduled(cron = "#{fetchSymbolsPriceSchedulerProperties.cron}") // Every 10 seconds
    @SchedulerLock(name = "#{fetchSymbolsPriceSchedulerProperties.name}", lockAtMostFor = "#{fetchSymbolsPriceSchedulerProperties.lock.atMostFor}", lockAtLeastFor = "#{fetchSymbolsPriceSchedulerProperties.lock.atLeastFor}")
    public void priceAggregationTask() {
        String symbols = httpHelper.buildParam(List.of("BTCUSDT", "ETHUSDT"));
        var test = binanceHttpClient.getBookTicker(symbols);
        System.out.println(test);
    }
}
