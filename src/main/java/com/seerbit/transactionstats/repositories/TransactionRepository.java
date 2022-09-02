package com.seerbit.transactionstats.repositories;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.seerbit.transactionstats.entities.Transaction;
import com.seerbit.transactionstats.interfaces.ITransactionRepository;
import com.seerbit.transactionstats.utils.HelperUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionRepository implements ITransactionRepository {

  private final DataStore dataStore;

  @Override
  public void saveTransaction(Transaction transaction) {
    dataStore.getDataStore().put(UUID.randomUUID(), transaction);
  }

  @Override
  public void deleteAll() {
    dataStore.getDataStore().clear();
  }

  @Override
  public Set<Entry<UUID, Transaction>> getAll() {
    return dataStore.getDataStore().entrySet();
  }

  public Set<Entry<UUID, Transaction>> getTrasnsactionsIn(Duration duration) {
    // LocalDateTime nowNow = LocalDateTime.now();
    LocalDateTime nowNow = LocalDateTime.of(2022, 9, 2, 5, 5, 20);
    LocalDateTime secondsBefore = nowNow.minusSeconds(duration.toSeconds());

    if (dataStore.getDataStore().isEmpty()) {
      return Collections.emptySet();
    }

    return dataStore
      .getDataStore()
      .entrySet()
      .stream()
      .filter(
        x -> {
          String timestamp = x.getValue().getTimestamp();
          LocalDateTime thisTime = HelperUtils.convertToLocalDateTime(
            timestamp
          );
          return (
            (thisTime.isAfter(secondsBefore) || thisTime.isEqual(secondsBefore)) &&
            (thisTime.isBefore(nowNow) || thisTime.isEqual(nowNow))
          );
        }
      )
      .collect(Collectors.toSet());
  }
}
