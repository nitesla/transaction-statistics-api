package com.gbeminiyi.seerbitinterview.transactionstatistics.services;

import com.gbeminiyi.seerbitinterview.transactionstatistics.commons.Mocker;
import com.gbeminiyi.seerbitinterview.transactionstatistics.entities.Transaction;
import com.gbeminiyi.seerbitinterview.transactionstatistics.interfaces.ITransactionRepository;
import com.gbeminiyi.seerbitinterview.transactionstatistics.models.StatisticsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {

  @InjectMocks
  TransactionService transactionService;

  @Mock
  ITransactionRepository transactionRepository;

  @Mock
  ConcurrentHashMap<UUID, Transaction> transactionStore;

  @Test
  void saveTransactionAddsANewTransactionToTheDataStore() {
    Transaction transaction = Mocker.getTransaction();
    doNothing().when(transactionRepository).saveTransaction(transaction);
    transactionService.saveTransaction(Mocker.getRequest("WITH_TIMESTAMP"));
    verify(transactionRepository).saveTransaction(transaction);
  }

  @Test
  void deleteTransactionsEmptiesTheDataStore() {
    doNothing().when(transactionRepository).deleteAll();
    transactionService.deleteAll();
    verify(transactionRepository).deleteAll();
  }

  @Test
  void getStatisticsReturnsASummaryOfTransactions() {
    ConcurrentHashMap<UUID, Transaction> map = new ConcurrentHashMap<>();
    map.put(UUID.randomUUID(), Mocker.getTransaction());
    when(transactionRepository.getTrasnsactionsIn(Duration.ofSeconds(30)))
      .thenReturn(map.entrySet());
    StatisticsResponse statisticsResponse = transactionService.getStatistics();
    Assertions.assertEquals(statisticsResponse, Mocker.getStatistics());
  }
}
