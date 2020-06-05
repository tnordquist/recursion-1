package edu.cnm.deepdive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PalindromesTest {

  static final String[] palindromes = {
      "radar",
      "abba",
      "x",
      "",
  };

  static final String[] nonPalindromes = {
      "sonar",
      "abb"
  };

  @Test
  void testRecursive() {
    for (String testCase : palindromes) {
      assertTrue(Palindromes.testRecursive(testCase));
    }
    for (String testCase : nonPalindromes) {
      assertFalse(Palindromes.testRecursive(testCase));
    }
  }

}