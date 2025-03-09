package com.lamdnt.cryptotrading.secheduler;

import com.lamdnt.cryptotrading.entity.TradingPair;
import com.lamdnt.cryptotrading.service.PriceAggregationService;
import com.lamdnt.cryptotrading.service.TradingPairService;
import com.lamdnt.cryptotrading.utils.TransactionHelper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FetchPriceScheduler {
  private final PriceAggregationService priceAggregationService;
  private final TradingPairService tradingPairService;
  private final TransactionHelper transactionHelper;

  @Scheduled(cron = "#{fetchSymbolsPriceSchedulerProperties.cron}") // Every 10 seconds
  @SchedulerLock(
      name = "#{fetchSymbolsPriceSchedulerProperties.name}",
      lockAtMostFor = "#{fetchSymbolsPriceSchedulerProperties.lock.atMostFor}",
      lockAtLeastFor = "#{fetchSymbolsPriceSchedulerProperties.lock.atLeastFor}")
  public void priceAggregationTask() {
    List<TradingPair> tradingPairs = tradingPairService.getAll();

    Map<String, Pair<BigDecimal, BigDecimal>> prices =
        priceAggregationService.getAggregationPrice(
            tradingPairs.stream().map(TradingPair::getSymbol).toList());

    log.info("Updating prices for {} trading pairs", tradingPairs.size());
    tradingPairs.forEach(
        tradingPair -> {
          Pair<BigDecimal, BigDecimal> price = prices.get(tradingPair.getSymbol());
          updateTradingPairPrice(tradingPair, price.getFirst(), price.getSecond());
        });
  }

  public void updateTradingPairPrice(
      TradingPair tradingPair, BigDecimal askPrice, BigDecimal bidPrice) {
    tradingPair.setAskPrice(askPrice);
    tradingPair.setBidPrice(bidPrice);
    transactionHelper.newTransaction(() -> tradingPairService.update(tradingPair));
  }
}
