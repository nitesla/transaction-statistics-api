package com.gbeminiyi.seerbitinterview.transactionstatistics;

import java.util.*;

public class AlgorithmSolutions {


  /*  1. Given an array of integers and a value, determine if there are any two integers in the
         array whose sum is equal to the given value. */

  static class findSum {
    //    In this solution, the algorithm to find a pair that add up to the target (val).
    public static boolean findSumOfTwo(int[] A, int val) {

      //    Scan the whole array once and store visited elements in a hash set.
      Set<Integer> foundValues = new HashSet<>();
      //   During scan, for every element a in the array, we check if val - a is present in the hash set i.e. val - a is already visited.
      for (int a : A) {
        //    If val - a is found in the hash set, it means there is a pair (a, val - a) in array whose sum is equal to the given val.
        if (foundValues.contains(val - a)) {
          return true;
        }

        foundValues.add(a);
      }
      //   If we have exhausted all elements in the array and didn’t find any such pair, the function will return false.
      return false;
    }

    // Test Algorithm 1
    public static void main(String[] args) {
      int[] v = new int[]{5, 7, 1, 2, 8, 4, 3};
      int[] test = new int[]{3, 20, 1, 2, 7};

      for (int i = 0; i < test.length; i++) {
        boolean output = findSumOfTwo(v, test[i]);
        System.out.println("findSumOfTwo(v, " + test[i] + ") = " + (output ? "true" : "false"));
      }
    }
    /*  Runtime Complexity
        The runtime complexity of this solution is linear, O(n).

        Memory Complexity
        The memory complexity of this solution is linear, O(n).
    */
  }





  /*   2. Given a sorted array of integers, return the low and high index of the given key. Return
          -1 if not found. The array length can be in the millions with many duplicates. */

  static class FindLowHigh {

/*  Linearly scanning the sorted array for low and high indices are highly inefficient since our array size can be in millions.
    Instead, I used a slightly modified binary search to find the low and high indices of a given key.

    I need to do binary search twice;
    The first binary search for finding the low index and the second binary search for finding the high index.  */


    // Low index
    public static int findLowIndex(List<Integer> arr, int key) {
      //     At every step, consider the array between low and high indices and calculate the mid index.
      int low = 0;
      int high = arr.size() - 1;
      int mid = high / 2;

      //    If the element at mid index is less than the key, low becomes mid + 1 (to move towards the start of range).
      //    If the element at mid is greater or equal to the key, the high becomes mid - 1. Index at low remains the same.
      while (low <= high) {

        int mid_elem = arr.get(mid);

        if (mid_elem < key) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }

        mid = low + (high - low) / 2;
      }

      //    When low is greater than high, low would be pointing to the first occurrence of the key.
      if (low < arr.size() && arr.get(low) == key) {
        return low;
      }

      //    If the element at low does not match the key, return -1.
      return -1;
    }

    //  High index
    public static int findHighIndex(List<Integer> arr, int key) {
      //  Similarly, I can find the high index by slightly modifying the above condition:
      int low = 0;
      int high = arr.size() - 1;
      int mid = high / 2;

      //    switch the low index to mid + 1 when element at mid index is less than or equal to the key.
      //    switch the high index to mid - 1 when the element at mid is greater than the key.
      while (low <= high) {

        int mid_elem = arr.get(mid);

        if (mid_elem <= key) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }

        mid = low + (high - low) / 2;
      }

      if (high == -1) {
        return high;
      }

      if (high < arr.size() && arr.get(high) == key) {
        return high;
      }

      return -1;
    }

    // Test Algorithm 2
    public static void main(String[] args) {
      List<Integer> array = Arrays.asList(1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6);
      int key = 3;
      int low = findLowIndex(array, key);
      int high = findHighIndex(array, key);
      System.out.println("Low Index of " + key + ": " + low);
      System.out.println("High Index of " + key + ": " + high);

      key = -2;
      low = findLowIndex(array, key);
      high = findHighIndex(array, key);
      System.out.println("Low Index of " + key + ": " + low);
      System.out.println("High Index of " + key + ": " + high);
    }
    /*  Runtime complexity
        Since we are using binary search, the runtime complexity is logarithmic, O(logn).

        Memory complexity
        The memory complexity is constant, O(1) since no extra storage is being used.
    */
  }





  /*   3. You are given an array (list) of interval pairs as input where each interval has a start
          and end timestamp. The input array is sorted by starting timestamps. You are required
          to merge overlapping intervals and return output array (list). */

  static class overlappingIntervals {

    // This problem can be solved in a simple linear scan algorithm.
    public static class Pair {
      public int first;
      public int second;

      public Pair(int x, int y) {
        this.first = x;
        this.second = y;
      }
    }

    public static class MergeIntervals {
      static ArrayList<Pair> mergeIntervals(ArrayList<Pair> v) {

        // List of input intervals is given, and we’ll keep merged intervals in the output list.
        if (v == null || v.size() == 0) {
          return null;
        }

        ArrayList<Pair> result = new ArrayList<Pair>();

        result.add(new Pair(v.get(0).first, v.get(0).second));


        //  For each interval in the input list:
        //  If the input interval is overlapping with the last interval in output list then we’ll merge these two intervals and update the last interval of output list with merged interval.
        //  Otherwise, we’ll add an input interval to the output list.
        for (int i = 1; i < v.size(); i++) {
          int x1 = v.get(i).first;
          int y1 = v.get(i).second;
          int x2 = result.get(result.size() - 1).first;
          int y2 = result.get(result.size() - 1).second;

          if (y2 >= x1) {
            result.get(result.size() - 1).second = Math.max(y1, y2);
          } else {
            result.add(new Pair(x1, y1));
          }
        }

        return result;
      }

      // Test Algorithm 3
      public static void main(String[] args) {
        ArrayList<Pair> v = new ArrayList<Pair>();

        v.add(new Pair(1, 5));
        v.add(new Pair(3, 7));
        v.add(new Pair(4, 6));
        v.add(new Pair(6, 8));
        v.add(new Pair(10, 12));
        v.add(new Pair(11, 15));
        v.add(new Pair(210, 212));
        v.add(new Pair(211, 215));

        ArrayList<Pair> result = mergeIntervals(v);

        for (int i = 0; i < result.size(); i++) {
          System.out.print(String.format("[%d, %d] ", result.get(i).first, result.get(i).second));
        }
      }
      /*  Runtime complexity
          The runtime complexity of this solution is linear, O(n).

          Memory complexity
          The memory complexity of this solution is linear, O(n).
      */
    }
  }
}
