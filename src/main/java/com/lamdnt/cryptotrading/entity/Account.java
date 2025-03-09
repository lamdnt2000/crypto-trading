package com.lamdnt.cryptotrading.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "account")
@NoArgsConstructor
public class Account extends Auditing<Long> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private List<Wallet> wallets;

  @Override
  public Long getId() {
    return id;
  }
}
