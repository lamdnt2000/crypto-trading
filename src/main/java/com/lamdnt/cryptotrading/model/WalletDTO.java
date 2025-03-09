package com.lamdnt.cryptotrading.model;

import com.lamdnt.cryptotrading.entity.CurrencyDTO;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalletDTO {
  private CurrencyDTO currency;
  private BigDecimal balance;
}
