package com.seerbit.transactionstats.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.seerbit.transactionstats.commons.Mocker;

@ExtendWith(SpringExtension.class)
public class TransactionRepositoryTests {

  @InjectMocks
  TransactionRepository transactionRepository;

  @Autowired
  DataStore transactionStore;

  @Test
  public void saveTransactionAddsANewValueToTheStore() {
    transactionRepository.saveTransaction(Mocker.getTransaction());
    assertEquals(transactionRepository.getAll().size(), 1);
  }
}
