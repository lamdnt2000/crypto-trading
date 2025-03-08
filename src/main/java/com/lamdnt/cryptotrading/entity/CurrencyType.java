package com.lamdnt.cryptotrading.entity;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CurrencyType {
    ETH("ETH"),
    BTC("BTC"),
    USDT("USDT");

    private final String value;

    private static final Map<String, CurrencyType> VALUE_MAP = Arrays.stream(CurrencyType.values())
            .collect(Collectors.toMap(CurrencyType::getValue, Function.identity()));

    CurrencyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CurrencyType fromValue(String value) {
        CurrencyType currencyType = VALUE_MAP.get(value);
        if (currencyType == null) {
            throw new IllegalArgumentException("Invalid CurrencyType: " + value);
        }
        return currencyType;
    }
}
