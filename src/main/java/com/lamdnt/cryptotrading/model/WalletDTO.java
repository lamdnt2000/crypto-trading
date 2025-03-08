package com.lamdnt.cryptotrading.model;

import com.lamdnt.cryptotrading.entity.Currency;
import com.lamdnt.cryptotrading.entity.CurrencyDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class WalletDTO {
    private CurrencyDTO currency;
    private BigDecimal balance;
}
