package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/longest-palindromic-subsequence/
//
// Given a string s, find the longest palindromic subsequence's length in s.
// A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without
// changing the order of the remaining elements.
//
// Example 1:
//
// Input: s = "bbbab"
// Output: 4
// Explanation: One possible longest palindromic subsequence is "bbbb".
// Example 2:
//
// Input: s = "cbbd"
// Output: 2
// Explanation: One possible longest palindromic subsequence is "bb".
//
//
// Constraints:
//
// 1 <= s.length <= 1000
// s consists only of lowercase English letters.
//
// Complexity:
// 	algorithmic O(?)
// 	memory O(?)
class LongestPalindromicSubsequenceTests {

    public int longestPalindromeSubseq(String s) {
        if (isPalindrome(s)) {
            return s.length();
        }

        return getSubPalindromeLength(s);
    }

    public int getSubPalindromeLength(String s) {
        int max = 1;

        HashSet<String> substrings = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(0, i) + s.substring(i + 1);
            if (isPalindrome(sub)) {
                return sub.length();
            }
            substrings.add(sub);
        }

        for (String sub : substrings) {
            max = Math.max(max, longestPalindromeSubseq(sub));
        }

        return max;
    }

    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    @Test
    void testCase1() {
        assertEquals(4, longestPalindromeSubseq("bbbab"));
    }

    @Test
    void testCase2() {
        assertEquals(2, longestPalindromeSubseq("cbbd"));
    }

    @Test
    void testCase3() {
        assertEquals(2, longestPalindromeSubseq("bb"));
    }

    @Test
    void testCase4() {
        assertEquals(7, longestPalindromeSubseq("bababab"));
    }

    @Test
    void testCase5() {
        assertEquals(6, longestPalindromeSubseq("babbab"));
    }

    @Test
    void testCase6() {
        assertEquals(1, longestPalindromeSubseq("b"));
    }

}
