package com.lamdnt.cryptotrading.entity;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum TransactionType {
    BID("BID"),
    ASK("ASK");

    private final String value;

    private static final Map<String, TransactionType> VALUE_MAP = Arrays.stream(TransactionType.values())
            .collect(Collectors.toMap(TransactionType::getValue, Function.identity()));

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TransactionType fromValue(String value) {
        TransactionType currencyType = VALUE_MAP.get(value);
        if (currencyType == null) {
            throw new IllegalArgumentException("Invalid TransactionType: " + value);
        }
        return currencyType;
    }
}
