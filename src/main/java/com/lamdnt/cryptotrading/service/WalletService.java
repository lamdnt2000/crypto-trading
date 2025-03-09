package com.lamdnt.cryptotrading.service;

import com.lamdnt.cryptotrading.entity.Account;
import com.lamdnt.cryptotrading.entity.Currency;
import com.lamdnt.cryptotrading.entity.Wallet;
import com.lamdnt.cryptotrading.repository.WalletRepository;
import com.lamdnt.cryptotrading.utils.TransactionHelper;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletService {
  private final WalletRepository walletRepository;
  private final TransactionHelper transactionHelper;

  public Wallet getOrCreateWallet(Account account, Currency currency) {
    return walletRepository
        .findByAccountAndCurrency(account.getUsername(), currency.getName())
        .orElseGet(
            () -> {
              Wallet wallet =
                  new Wallet()
                      .setAccount(account)
                      .setCurrency(currency)
                      .setBalance(BigDecimal.ZERO);
              return transactionHelper.newTransaction(() -> walletRepository.save(wallet));
            });
  }

  public void saveAll(List<Wallet> wallets) {
    walletRepository.saveAll(wallets);
  }
}
