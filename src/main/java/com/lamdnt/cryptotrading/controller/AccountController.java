package com.lamdnt.cryptotrading.controller;

import com.lamdnt.cryptotrading.mapper.AccountMapper;
import com.lamdnt.cryptotrading.model.AccountDTO;
import com.lamdnt.cryptotrading.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
@Tag(name = "Account")
@RequestMapping("/api")
public class AccountController {
  private final AccountService accountService;
  private final AccountMapper accountMapper;

  @GetMapping("accounts/{username}/wallets")
  @Operation(summary = "Get crypto wallet balances", description = "Lists cryptocurrency wallet balances for a user")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "List of crypto wallets"),
          @ApiResponse(responseCode = "404", description = "Account not found")
  })
  public AccountDTO getAccountWallets(@PathVariable("username") String username) {
    return accountService
        .findByUsernameWithWallets(username)
        .map(accountMapper::toDTO)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
  }
}
