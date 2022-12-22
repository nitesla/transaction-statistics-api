package com.gbeminiyi.seerbitinterview.transactionstatistics.interfaces;

import com.gbeminiyi.seerbitinterview.transactionstatistics.models.CreateTransactionRequest;

public interface ITransactionService {
  public void saveTransaction(CreateTransactionRequest createTransactionRequest);

  public void deleteAll();
}
