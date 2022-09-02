package com.seerbit.transactionstats.interfaces;

import java.time.Duration;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.seerbit.transactionstats.entities.Transaction;

public interface ITransactionRepository {
  public void saveTransaction(Transaction transaction);

  public void deleteAll();

  public Set<Entry<UUID, Transaction>> getTrasnsactionsIn(Duration duration);

  public Set<Entry<UUID, Transaction>> getAll();
}
