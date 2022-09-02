package com.seerbit.transactionstats;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AlgorithmSolutions {

  public static int maximumSubArray(int[] values) {
    int maximumXor = 0;

    for (int i = 0; i < values.length; i++) {
      int xorValAtIndex = values[i];
      for (int j = i; j < values.length; j++) {
        xorValAtIndex = xorValAtIndex ^ values[j];
        maximumXor = Math.max(maximumXor, xorValAtIndex);
      }
    }
    return maximumXor;
  }

  public static Timestamp overlappingIntervals(List<Timestamp> timestamps) {
    Timestamp interval = new Timestamp();

    timestamps
      .stream()
      .forEach(
        x -> {
          if (interval.lower == null) {
            interval.lower = x.lower;
            interval.upper = x.upper;
          } else {
            if (x.lower < interval.lower) {
              interval.lower = x.lower;
            }
            if (x.upper > interval.upper) {
              interval.upper = x.upper;
            }
          }
        }
      );
    return interval;
  }

  static class Timestamp {

    Integer upper, lower;

    Timestamp() {}

    Timestamp(int upper, int lower) {
      this.upper = upper;
      this.lower = lower;
    }
  }

  //DRIVER CODE
//   public static void main(String args[]) {
//     int arr[] = new int[] { 8, 1, 2, 12 };
//     System.out.println(maximumSubArray(arr));

//     List<Timestamp> timestamps = new ArrayList<>();
//     timestamps.add(new Timestamp(70, 20));
//     timestamps.add(new Timestamp(50, 10));
//     timestamps.add(new Timestamp(30, 5));
//     timestamps.add(new Timestamp(20, 1));

//     Timestamp timestamp = overlappingIntervals(timestamps);

//     System.out.println(timestamp.lower);
//     System.out.println(timestamp.upper);
//   }
}
