package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/longest-palindromic-substring/description/
// Given a string s, return the longest palindromic substring in s.

// Example 1:
// Input: s = "babad"
// Output: "bab"
// Explanation: "aba" is also a valid answer.

// Example 2:
// Input: s = "cbbd"
// Output: "bb"

// Complexity:
// 	algorithmic O(n^3)
// 	memory O(1)
class LongestPalindromicSubstringTests {

    public String longestPalindrome(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++)
            for (int j = i + result.length() + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                if (isPalindromic(substring) && substring.length() > result.length()) {
                    result = substring;
                }
            }
        return result;
    }

    private boolean isPalindromic(String s) {
        if (s.length() == 1) return true;

        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) return false;
        }
        return true;
    }

    @Test
    void Test1() {
        assertEquals("bab", longestPalindrome("babad"));
    }

    @Test
    void Test2() {
        assertEquals("bb", longestPalindrome("cbbd"));
    }

    @Test
    void Test3() {
        assertEquals("abcba", longestPalindrome("abcba"));
    }

    @Test
    void Test4() {
        assertEquals("a", longestPalindrome("a"));
    }

    @Test
    void Test5() {
        assertEquals("a", longestPalindrome("ac"));
    }

}
