package com.gbeminiyi.seerbitinterview.transactionstatistics.controllers;

import com.gbeminiyi.seerbitinterview.transactionstatistics.models.CreateTransactionRequest;
import com.gbeminiyi.seerbitinterview.transactionstatistics.repositories.TransactionRepository;
import com.gbeminiyi.seerbitinterview.transactionstatistics.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class TransactionControllers {

  private final TransactionService transactionService;
  private final TransactionRepository transactionRepository;

  @PostMapping("transaction")
  public ResponseEntity<?> createTransaction(
    @RequestBody @Valid CreateTransactionRequest createTransactionRequest
  ) {
    transactionService.saveTransaction(createTransactionRequest);
    return ResponseEntity.created(null).build();
  }

  @GetMapping("transaction")
  public ResponseEntity<?> getStatistics() {
    return ResponseEntity.ok(transactionService.getStatistics());
  }

  @DeleteMapping("transaction")
  public ResponseEntity<?> deleteTransactions() {
    transactionService.deleteAll();
    return ResponseEntity.noContent().build();
  }
}
