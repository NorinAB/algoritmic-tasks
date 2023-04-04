package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/optimal-partition-of-string/
// Given a string s, partition the string into one or more substrings such that the characters in each substring are unique.
// That is, no letter appears in a single substring more than once.
// Return the minimum number of substrings in such a partition.
// Note that each character should belong to exactly one substring in a partition.
//
// Example 1:
// Input: s = "abacaba"
// Output: 4
// Explanation:
// Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
// It can be shown that 4 is the minimum number of substrings needed.
//
// Example 2:
// Input: s = "ssssss"
// Output: 6
// Explanation:
// The only valid partition is ("s","s","s","s","s","s").
//
// Constraints:
//
// 1 <= s.length <= 105
// s consists of only English lowercase letters.

// Complexity:
// 	algorithmic O(n)
// 	memory O(1) no more than O(26)
class OptimalPartitionOfStringTests {

    public int partitionString(String s) {
        int count = 1;
        Set<Character> storage = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (storage.contains(c)) {
                count++;
                storage.clear();
            }
            storage.add(c);
        }
        return count;
    }

    @Test
    void Test1() {
        assertEquals(4, partitionString("abacaba"));
    }

    @Test
    void Test2() {
        assertEquals(6, partitionString("ssssss"));
    }

    @Test
    void Test3() {
        assertEquals(1, partitionString("a"));
    }

    @Test
    void Test4() {
        assertEquals(3, partitionString("ababab"));
    }

    @Test
    void Test5() {
        assertEquals(2, partitionString("abcba"));
    }

}
