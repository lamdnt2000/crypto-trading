package com.lamdnt.cryptotrading.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "trading_pair")
@NoArgsConstructor
public class TradingPair extends Auditing<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_currency_id")
    private Currency baseCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_currency_id")
    private Currency quoteCurrency;

    @Column(name = "bid_price", nullable = false)
    private BigDecimal bidPrice;

    @Column(name = "ask_price", nullable = false)
    private BigDecimal askPrice;

    @Column(name = "symbol")
    private String symbol;

    @Override
    public Long getId() {
        return id;
    }
}
