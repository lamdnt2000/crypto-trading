package com.lamdnt.cryptotrading.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class AccountDTO implements Serializable {
    private String username;
    private List<WalletDTO> wallets;
}
