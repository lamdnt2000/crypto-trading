package com.lamdnt.cryptotrading.controller;

import com.lamdnt.cryptotrading.mapper.TransactionMapper;
import com.lamdnt.cryptotrading.model.TransactionRequest;
import com.lamdnt.cryptotrading.model.TransactionResponse;
import com.lamdnt.cryptotrading.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @PostMapping("tradings/{username}/transactions")
    public void createTransaction(@RequestBody @Validated TransactionRequest transactionRequest, @PathVariable("username") String username) {
        transactionService.createTransaction(transactionRequest, username);
    }

    @GetMapping("tradings/{username}/transactions")
    public List<TransactionResponse> getTransaction(@PathVariable("username")  String username) {
        return transactionService.getTransactionsByUsername(username).stream().map(transactionMapper::toTransactionResponse).toList();
    }
}
