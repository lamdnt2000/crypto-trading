package com.lamdnt.cryptotrading.model;

import com.lamdnt.cryptotrading.entity.Account;
import com.lamdnt.cryptotrading.entity.TradingPair;
import com.lamdnt.cryptotrading.entity.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransactionResponse implements Serializable {
    private Long id;
    private TransactionType type;
    private String symbol;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal quoteQuantity;
    private LocalDateTime createdAt;
}
