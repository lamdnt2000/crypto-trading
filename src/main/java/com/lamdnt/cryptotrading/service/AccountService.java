package com.lamdnt.cryptotrading.service;

import com.lamdnt.cryptotrading.entity.Account;
import com.lamdnt.cryptotrading.repository.AccountRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
  private final AccountRepository accountRepository;

  public Optional<Account> findByUsernameWithWallets(String username) {
    return accountRepository.findByUsernameWithWallets(username);
  }
}
