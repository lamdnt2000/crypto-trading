package com.lamdnt.cryptotrading.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO implements Serializable {
  private String username;
  private List<WalletDTO> wallets;
}
