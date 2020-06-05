package edu.cnm.deepdive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FactorialsTest {

  static final long[][] testCases = {
      {0, 1},
      {1, 1},
      {5, 120},
      {10, 3628800},
      {13, 6227020800L}
  };

  @Test
  void computeRecursive() {
    for (long[] testCase : testCases) {
      assertEquals(testCase[1], Factorials.computeRecursive((int) testCase[0]));
    }
    assertThrows(IllegalArgumentException.class, () -> Factorials.computeRecursive(-1));
  }

}