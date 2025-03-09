package com.lamdnt.cryptotrading.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "wallet")
@NoArgsConstructor
public class Wallet extends Auditing<Long> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "currency_id")
  private Currency currency;

  @Column(name = "balance")
  private BigDecimal balance;

  @Override
  public Long getId() {
    return id;
  }

  public boolean isSufficient(BigDecimal amount) {
    return balance.compareTo(amount) >= 0;
  }
}
