package com.gbeminiyi.seerbitinterview.transactionstatistics.repositories;

import com.gbeminiyi.seerbitinterview.transactionstatistics.entities.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataStore {

  private ConcurrentHashMap<UUID, Transaction> dataStore;

  @Bean
  public void createDataStore() {
    dataStore = new ConcurrentHashMap<UUID, Transaction>();
  }

  public ConcurrentHashMap<UUID, Transaction> getDataStore() {
    return dataStore;
  }
}
