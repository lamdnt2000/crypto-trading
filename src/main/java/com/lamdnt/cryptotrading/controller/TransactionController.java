package com.lamdnt.cryptotrading.controller;

import com.lamdnt.cryptotrading.mapper.TransactionMapper;
import com.lamdnt.cryptotrading.model.TransactionRequest;
import com.lamdnt.cryptotrading.model.TransactionResponse;
import com.lamdnt.cryptotrading.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "Transaction")
@RequestMapping("/api")
public class TransactionController {
  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;

  @PostMapping("tradings/{username}/transactions")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(
      summary = "Create a new transaction",
      description = "Initiates a trade for the specified user")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Transaction created"),
    @ApiResponse(responseCode = "400", description = "Invalid request data"),
    @ApiResponse(responseCode = "404", description = "User not found")
  })
  public void createTransaction(
      @RequestBody @Validated TransactionRequest transactionRequest,
      @PathVariable("username") String username) {
    transactionService.createTransaction(transactionRequest, username);
  }

  @GetMapping("tradings/{username}/transactions")
  @Operation(
      summary = "Get transaction history",
      description = "Retrieves all transactions for a user")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "List of transactions"),
  })
  public List<TransactionResponse> getTransaction(@PathVariable("username") String username) {
    return transactionService.getTransactionsByUsername(username).stream()
        .map(transactionMapper::toTransactionResponse)
        .toList();
  }
}
