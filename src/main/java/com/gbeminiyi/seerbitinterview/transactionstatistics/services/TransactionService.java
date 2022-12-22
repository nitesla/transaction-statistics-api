package com.gbeminiyi.seerbitinterview.transactionstatistics.services;

import com.gbeminiyi.seerbitinterview.transactionstatistics.entities.Transaction;
import com.gbeminiyi.seerbitinterview.transactionstatistics.interfaces.ITransactionRepository;
import com.gbeminiyi.seerbitinterview.transactionstatistics.interfaces.ITransactionService;
import com.gbeminiyi.seerbitinterview.transactionstatistics.interfaces.ITransactionStatistics;
import com.gbeminiyi.seerbitinterview.transactionstatistics.models.CreateTransactionRequest;
import com.gbeminiyi.seerbitinterview.transactionstatistics.models.StatisticsResponse;
import com.gbeminiyi.seerbitinterview.transactionstatistics.utils.HelperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Map.Entry;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService
  implements ITransactionService, ITransactionStatistics {

  private final ITransactionRepository transactionRepository;

  @Override
  public StatisticsResponse getStatistics() {
    StatisticsResponse statisticsResponse = null;
    Set<Entry<UUID, Transaction>> transactions = transactionRepository.getTrasnsactionsIn(
      Duration.ofSeconds(30)
    );

    if (transactions.isEmpty()) {
      return new StatisticsResponse();
    }

    int count = transactions.size();

    List<BigDecimal> amountVals = transactions
      .stream()
      .map(x -> x.getValue().getAmount())
      .collect(Collectors.toList());

    BigDecimal sum = amountVals
      .stream()
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal avg = sum.divide(new BigDecimal(count));

    CompletableFuture<OptionalDouble> max = CompletableFuture.supplyAsync(
      () -> amountVals.stream().mapToDouble(x -> x.doubleValue()).max()
    );
    CompletableFuture<OptionalDouble> min = CompletableFuture.supplyAsync(
      () -> amountVals.stream().mapToDouble(x -> x.doubleValue()).min()
    );

    statisticsResponse =
      StatisticsResponse
        .builder()
        .sum(HelperUtils.roundAmount(sum.doubleValue()))
        .avg(HelperUtils.roundAmount(avg.doubleValue()))
        .count(count)
        .build();

    try {
      statisticsResponse.setMin(
        HelperUtils.roundAmount(min.get().getAsDouble())
      );
      statisticsResponse.setMax(
        HelperUtils.roundAmount(max.get().getAsDouble())
      );
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    return statisticsResponse;
  }

  @Override
  public void saveTransaction(
    CreateTransactionRequest createTransactionRequest
  ) {
    Transaction transaction = Transaction
      .builder()
      .amount(new BigDecimal(createTransactionRequest.getAmount()))
      .timestamp(createTransactionRequest.getTimestamp())
      .build();

    transactionRepository.saveTransaction(transaction);
  }

  @Override
  public void deleteAll() {
    transactionRepository.deleteAll();
  }
}
