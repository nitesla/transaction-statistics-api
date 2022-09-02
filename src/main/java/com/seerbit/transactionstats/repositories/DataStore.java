package com.seerbit.transactionstats.repositories;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.seerbit.transactionstats.entities.Transaction;

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
