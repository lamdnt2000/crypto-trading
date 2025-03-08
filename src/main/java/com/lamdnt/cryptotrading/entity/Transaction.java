package com.lamdnt.cryptotrading.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "transaction")
@NoArgsConstructor
public class Transaction extends Auditing<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trading_pair_id")
    private TradingPair tradingPair;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "quote_quantity")
    private BigDecimal quoteQuantity;

    @Override
    public Long getId() {
        return id;
    }

    public boolean isBuy() {
        return type == TransactionType.ASK;
    }

    public boolean isSell() {
        return type == TransactionType.BID;
    }
}
