package com.gbeminiyi.seerbitinterview.transactionstatistics.interfaces;

import com.gbeminiyi.seerbitinterview.transactionstatistics.entities.Transaction;

import java.time.Duration;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public interface ITransactionRepository {
  public void saveTransaction(Transaction transaction);

  public void deleteAll();

  public Set<Entry<UUID, Transaction>> getTrasnsactionsIn(Duration duration);

  public Set<Entry<UUID, Transaction>> getAll();
}
