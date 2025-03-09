package com.lamdnt.cryptotrading.connector;

import com.lamdnt.cryptotrading.model.HuobiTickerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "huobiClient")
public interface HuobiHttpClient {
  @GetMapping(value = "market/tickers", produces = MediaType.APPLICATION_JSON_VALUE)
  HuobiTickerResponse getTickers();
}
