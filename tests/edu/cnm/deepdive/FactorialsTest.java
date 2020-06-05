package edu.cnm.deepdive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FactorialsTest {

  static final int[][] testCases = {
      {0, 1},
      {1, 1},
      {5, 120},
      {10, 3628800}
  };

  @Test
  void computeRecursive() {
    for (int[] testCase : testCases) {
      assertEquals(testCase[1], Factorials.computeRecursive(testCase[0]));
    }
    assertThrows(IllegalArgumentException.class, () -> Factorials.computeRecursive(-1));
  }

}