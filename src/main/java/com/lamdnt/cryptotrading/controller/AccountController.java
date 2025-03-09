package com.lamdnt.cryptotrading.controller;

import com.lamdnt.cryptotrading.mapper.AccountMapper;
import com.lamdnt.cryptotrading.model.AccountDTO;
import com.lamdnt.cryptotrading.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class AccountController {
  private final AccountService accountService;
  private final AccountMapper accountMapper;

  @GetMapping("accounts/{username}/wallets")
  public AccountDTO getAccountWallets(@PathVariable("username") String username) {
    return accountService
        .findByUsernameWithWallets(username)
        .map(accountMapper::toDTO)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
  }
}
