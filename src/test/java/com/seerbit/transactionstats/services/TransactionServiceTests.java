package com.seerbit.transactionstats.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.seerbit.transactionstats.commons.Mocker;
import com.seerbit.transactionstats.entities.Transaction;
import com.seerbit.transactionstats.interfaces.ITransactionRepository;
import com.seerbit.transactionstats.models.StatisticsResponse;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    assertEquals(statisticsResponse, Mocker.getStatistics());
  }
}
