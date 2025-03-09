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
import java.math.RoundingMode;
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
            Optional.ofNullable(walletMap.get(tradingPair.getQuoteCurrency().getName().getValue()))
                    .ifPresentOrElse(quoteWallet -> {
                        if (quoteWallet.isSufficient(transactionRequest.getAmount())) {
                            Wallet wallet = Optional.ofNullable(walletMap.get(tradingPair.getBaseCurrency().getName()))
                                    .orElseGet(() -> new Wallet()
                                            .setCurrency(tradingPair.getBaseCurrency()))
                                            .setBalance(BigDecimal.ZERO)
                                            .setAccount(account);
                            wallet.setBalance(wallet.getBalance().add(tradingPair.getAskAmount(transactionRequest.getAmount())));
                            quoteWallet.setBalance(quoteWallet.getBalance().subtract(transactionRequest.getAmount()));
                            Transaction transaction = new Transaction()
                                    .setAccount(account)
                                    .setTradingPair(tradingPair)
                                    .setPrice(tradingPair.getAskPrice())
                                    .setQuantity(tradingPair.getAskAmount(transactionRequest.getAmount()))
                                    .setQuoteQuantity(transactionRequest.getAmount())
                                    .setType(TransactionType.ASK);
                            walletRepository.saveAll(List.of(wallet, quoteWallet));
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
        else if (transactionRequest.getType() == TransactionType.BID){
            Optional.ofNullable(walletMap.get(tradingPair.getBaseCurrency().getName().getValue()))
                    .ifPresentOrElse(baseCurrencyWallet -> {
                        if (baseCurrencyWallet.isSufficient(transactionRequest.getAmount())) {
                            Wallet quoteWallet = walletMap.get(tradingPair.getQuoteCurrency().getName().getValue());

                            quoteWallet.setBalance(quoteWallet.getBalance().add(tradingPair.getBidAmount(transactionRequest.getAmount())).setScale(8, RoundingMode.HALF_UP));
                            baseCurrencyWallet.setBalance(baseCurrencyWallet.getBalance().subtract(transactionRequest.getAmount()).setScale(8, RoundingMode.HALF_UP));
                            Transaction transaction = new Transaction()
                                    .setAccount(account)
                                    .setTradingPair(tradingPair)
                                    .setPrice(tradingPair.getBidPrice())
                                    .setQuantity(transactionRequest.getAmount())
                                    .setQuoteQuantity(tradingPair.getBidAmount(transactionRequest.getAmount()))
                                    .setType(TransactionType.BID);
                            walletRepository.saveAll(List.of(quoteWallet, baseCurrencyWallet));
                            transactionRepository.save(transaction);
                        } else {
                            throw new RuntimeException("Insufficient balance");
                        }
                    }, () -> {
                        throw new RuntimeException("Insufficient balance");
                    });
        }
        else {
            throw new RuntimeException("Invalid transaction type");
        }
    }

    public List<Transaction> getTransactionsByUsername(String username) {
        return transactionRepository.findByUsername(username);
    }
}
