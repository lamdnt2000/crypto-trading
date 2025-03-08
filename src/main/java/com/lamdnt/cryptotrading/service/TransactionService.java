package com.lamdnt.cryptotrading.service;

import com.lamdnt.cryptotrading.entity.*;
import com.lamdnt.cryptotrading.model.TransactionRequest;
import com.lamdnt.cryptotrading.repository.AccountRepository;
import com.lamdnt.cryptotrading.repository.TradingPairRepository;
import com.lamdnt.cryptotrading.repository.TransactionRepository;
import com.lamdnt.cryptotrading.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TradingPairRepository tradingPairRepository;
    private final AccountRepository accountRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public void createTransaction(TransactionRequest transactionRequest, String username) {
        Account account = accountRepository.findByUsernameWithWallets(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        TradingPair tradingPair = tradingPairRepository.findById(transactionRequest.getTradingPairId())
                .orElseThrow(() -> new RuntimeException("Trading pair not found"));

        Map<String, Wallet> walletMap = account.getWallets().stream()
                .collect(Collectors.toMap(wallet -> wallet.getCurrency().getName().getValue(), wallet -> wallet));

        if (transactionRequest.getType() == TransactionType.ASK){
            Optional.ofNullable(walletMap.get(CurrencyType.USDT.getValue()))
                    .ifPresentOrElse(usdtWallet -> {
                        if (usdtWallet.isSufficient(transactionRequest.getAmount())) {
                            Wallet wallet = Optional.ofNullable(walletMap.get(tradingPair.getBaseCurrency().getName()))
                                    .orElseGet(() -> new Wallet()
                                            .setCurrency(new Currency().setName(tradingPair.getBaseCurrency().getName()))
                                            .setBalance(BigDecimal.ZERO)
                                            .setAccount(account));
                            wallet.setBalance(wallet.getBalance().add(transactionRequest.getAmount()));
                            usdtWallet.setBalance(usdtWallet.getBalance().subtract(transactionRequest.getAmount()));
                            Transaction transaction = new Transaction()
                                    .setAccount(account)
                                    .setTradingPair(tradingPair)
                                    .setPrice(tradingPair.getAskPrice())
                                    .setQuantity(tradingPair.getAskAmount(transactionRequest.getAmount()))
                                    .setQuoteQuantity(transactionRequest.getAmount())
                                    .setType(TransactionType.ASK);
                            accountRepository.save(account);
                            transactionRepository.save(transaction);
                        } else {
                            throw new RuntimeException("Insufficient balance");
                        }
                    }, () -> {
                        Wallet wallet = new Wallet()
                                .setCurrency(new Currency().setName(CurrencyType.USDT))
                                .setBalance(BigDecimal.ZERO)
                                .setAccount(account);
                        walletRepository.save(wallet);
                    });
        }
//
//        account.getWallets().stream().filter(wallet -> wallet.getCurrency().getName().getValue().equals(transactionRequest.getTicker()))
//                .findFirst()
//                .ifPresentOrElse(wallet -> {
//                    // Create transaction
//                }, () -> {
//                    throw new RuntimeException("Wallet not found");
//                });
    }

    public List<Transaction> getTransactionsByUsername(String username) {
        return transactionRepository.findByUsername(username);
    }
}
