package com.gbeminiyi.seerbitinterview.transactionstatistics.repositories;

import com.gbeminiyi.seerbitinterview.transactionstatistics.entities.Transaction;
import com.gbeminiyi.seerbitinterview.transactionstatistics.interfaces.ITransactionRepository;
import com.gbeminiyi.seerbitinterview.transactionstatistics.utils.HelperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
