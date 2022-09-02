package com.seerbit.transactionstats.controllers;

import com.seerbit.transactionstats.models.CreateTransactionRequest;
import com.seerbit.transactionstats.repositories.TransactionRepository;
import com.seerbit.transactionstats.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class TransactionControllers {

  private final TransactionService transactionService;
  private final TransactionRepository transactionRepository;

  @PostMapping("transactions")
  public ResponseEntity<?> createTransaction(
    @RequestBody @Valid CreateTransactionRequest createTransactionRequest
  ) {
    transactionService.saveTransaction(createTransactionRequest);
    return ResponseEntity.created(null).build();
  }

  @GetMapping("statistics")
  public ResponseEntity<?> getStatistics() {
    return ResponseEntity.ok(transactionService.getStatistics());
  }

  @DeleteMapping("transactions")
  public ResponseEntity<?> deleteTransactions() {
    transactionService.deleteAll();
    return ResponseEntity.noContent().build();
  }

  //TO BE REMOVED
  @GetMapping("transactions")
  public ResponseEntity<?> getTrasnactions() {
    return ResponseEntity.ok(transactionRepository.getAll());
  }
}
