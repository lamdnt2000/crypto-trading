package com.lamdnt.cryptotrading.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "transaction")
@NoArgsConstructor
public class Transaction extends Auditing<Long> {
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

  @Column(name = "tx_id")
  private UUID txId;

  @Override
  public Long getId() {
    return id;
  }
}
