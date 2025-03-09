package com.lamdnt.cryptotrading.service;

import com.lamdnt.cryptotrading.entity.*;
import com.lamdnt.cryptotrading.model.TransactionRequest;
import com.lamdnt.cryptotrading.repository.AccountRepository;
import com.lamdnt.cryptotrading.repository.TradingPairRepository;
import com.lamdnt.cryptotrading.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final TradingPairRepository tradingPairRepository;
  private final AccountRepository accountRepository;
  private final WalletService walletService;

  public List<Transaction> getTransactionsByUsername(String username) {
    return transactionRepository.findByUsername(username);
  }

  @Transactional
  public void createTransaction(TransactionRequest transactionRequest, String username) {
    Account account =
        accountRepository
            .findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Account not found"));

    TradingPair tradingPair =
        tradingPairRepository
            .findById(transactionRequest.getTradingPairId())
            .orElseThrow(() -> new RuntimeException("Trading pair not found"));

    processTransaction(account, tradingPair, transactionRequest);
  }

  private void processTransaction(
      Account account, TradingPair tradingPair, TransactionRequest request) {
    TransactionType type = request.getType();
    boolean isAsk = type == TransactionType.ASK;

    // Determine source and destination wallets and amounts
    Currency sourceCurrency =
        isAsk ? tradingPair.getQuoteCurrency() : tradingPair.getBaseCurrency();
    Currency destCurrency = isAsk ? tradingPair.getBaseCurrency() : tradingPair.getQuoteCurrency();

    BigDecimal sourceAmount = request.getAmount();
    BigDecimal destAmount =
        isAsk ? tradingPair.getAskAmount(sourceAmount) : tradingPair.getBidAmount(sourceAmount);

    // Fetch or create wallets
    Wallet sourceWallet = walletService.getOrCreateWallet(account, sourceCurrency);
    Wallet destWallet = walletService.getOrCreateWallet(account, destCurrency);

    // Check balance and update wallets
    if (!sourceWallet.isSufficient(sourceAmount)) {
      throw new RuntimeException("Insufficient balance");
    }

    // Check balance and update wallets
    if (!sourceWallet.isSufficient(sourceAmount)) {
      throw new RuntimeException("Insufficient balance");
    }

    sourceWallet.setBalance(
        sourceWallet.getBalance().subtract(sourceAmount).setScale(8, RoundingMode.HALF_UP));
    destWallet.setBalance(
        destWallet.getBalance().add(destAmount).setScale(8, RoundingMode.HALF_UP));

    // Save wallets by order to prevent deadlock
    walletService.saveAll(sortWallets(List.of(sourceWallet, destWallet)));
    transactionRepository.save(createTransaction(account, tradingPair, request, type));
  }

  private Transaction createTransaction(
      Account account, TradingPair tradingPair, TransactionRequest request, TransactionType type) {
    boolean isAsk = type == TransactionType.ASK;
    return new Transaction()
        .setAccount(account)
        .setTradingPair(tradingPair)
        .setTxId(java.util.UUID.randomUUID())
        .setPrice(isAsk ? tradingPair.getAskPrice() : tradingPair.getBidPrice())
        .setQuantity(isAsk ? tradingPair.getAskAmount(request.getAmount()) : request.getAmount())
        .setQuoteQuantity(
            isAsk ? request.getAmount() : tradingPair.getBidAmount(request.getAmount()))
        .setType(type);
  }

  private List<Wallet> sortWallets(List<Wallet> wallets) {
    return wallets.stream().sorted(Comparator.comparing(Wallet::getId)).toList();
  }
}
