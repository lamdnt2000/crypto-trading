package com.lamdnt.cryptotrading.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO implements Serializable {
  private String username;
  private List<WalletDTO> wallets;
}
