package com.lamdnt.cryptotrading.model;

import com.lamdnt.cryptotrading.entity.CurrencyDTO;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WalletDTO {
  private CurrencyDTO currency;
  private BigDecimal balance;
}
