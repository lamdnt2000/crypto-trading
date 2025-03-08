package com.lamdnt.cryptotrading.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "wallet")
@NoArgsConstructor
public class Wallet extends Auditing<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "balance")
    private BigDecimal balance;

    @Override
    public Long getId() {
        return id;
    }
}
