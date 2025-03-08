package com.lamdnt.cryptotrading.connector;

import com.lamdnt.cryptotrading.model.BinanceBookTickerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "binanceClient")
public interface BinanceHttpClient {
    @GetMapping(
            value = "ticker/bookTicker",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<BinanceBookTickerResponse> getBookTicker(
            @RequestParam("symbols") String symbols
    );
}
