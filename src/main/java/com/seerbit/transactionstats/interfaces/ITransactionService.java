package com.seerbit.transactionstats.interfaces;

import com.seerbit.transactionstats.models.CreateTransactionRequest;

public interface ITransactionService {
  public void saveTransaction(CreateTransactionRequest createTransactionRequest);

  public void deleteAll();
}
