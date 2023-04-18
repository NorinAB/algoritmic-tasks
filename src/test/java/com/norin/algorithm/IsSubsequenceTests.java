package com.norin.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/is-subsequence/
//
// Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
//
// A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
//
// Example 1:
// Input: s = "abc", t = "ahbgdc"
// Output: true
//
// Example 2:
// Input: s = "axc", t = "ahbgdc"
// Output: false
//
// Constraints:
// 0 <= s.length <= 100
// 0 <= t.length <= 104
// s and t consist only of lowercase English letters.
//
// Complexity:
// 	algorithmic O(?)
// 	memory O(?)
class IsSubsequenceTests {

    public boolean isSubsequence(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;
        while (sIndex < s.length() && tIndex < t.length()) {
            if (t.charAt(tIndex) == s.charAt(sIndex)) {
                sIndex++;
            }
            tIndex++;
        }

        return sIndex == s.length();
    }

    @Test
    void testCase1() {
        Assertions.assertTrue(isSubsequence("abc", "ahbgdc"));
    }

    @Test
    void testCase2() {
        Assertions.assertFalse(isSubsequence("axc", "ahbgdc"));
    }

}
