package com.lamdnt.cryptotrading.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "currency")
@NoArgsConstructor
public class Currency extends Auditing<Long> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @Enumerated(EnumType.STRING)
  private CurrencyType name;

  @Override
  public Long getId() {
    return id;
  }
}
