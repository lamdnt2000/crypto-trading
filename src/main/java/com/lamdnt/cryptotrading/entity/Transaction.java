package com.lamdnt.cryptotrading.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "currency")
@NoArgsConstructor
public class Transaction extends Auditing<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "trading_pair_id")
    private TradingPair tradingPair;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Override
    public Long getId() {
        return id;
    }
}
