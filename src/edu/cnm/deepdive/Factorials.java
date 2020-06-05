package edu.cnm.deepdive;

public class Factorials {

  public static long computeRecursive(int n) {
    long result = 1;
    if (n < 0) {
      throw new IllegalArgumentException();
    } else if (n > 0) {
      result = n * computeRecursive(n - 1);
    }
    return result;
  }

}
