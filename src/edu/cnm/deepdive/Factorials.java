package edu.cnm.deepdive;

public class Factorials {

  public static long computeRecursive(int n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    return (n == 0) ? 1 : (n * computeRecursive(n - 1));
  }

}
