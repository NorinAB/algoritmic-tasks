package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/coin-change/

// You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
// Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
// You may assume that you have an infinite number of each kind of coin.
//
// Example 1:
// Input: coins = [1,2,5], amount = 11
// Output: 3
// Explanation: 11 = 5 + 5 + 1
//
// Example 2:
// Input: coins = [2], amount = 3
// Output: -1
//
// Example 3:
// Input: coins = [1], amount = 0
// Output: 0
//
// Constraints:
//
// 1 <= coins.length <= 12
// 1 <= coins[i] <= 231 - 1
// 0 <= amount <= 104
//
// Complexity:
// 	algorithmic O(n)
// 	memory O(n)
class CoinChangeTests {

    public int coinChange(Integer[] coins, int amount) {
        Arrays.sort(coins, Collections.reverseOrder());
        subCnt(coins, amount, 0);
        return subCnt(coins, amount, 0);
    }

    private int subCnt(Integer[] coins, int amount, int start) {
        while (start < coins.length && coins[start] > amount) start++;
        if (start == coins.length) return -1;
        if (amount == coins[start]) return 1;
        int current = start;
        int min = 100;
        while (++start < coins.length) {
            int subCnt = subCnt(coins, amount - coins[current], start);
            if (subCnt >= 0 && subCnt < min) {
                min = subCnt;
            }
        }

        return min == 100 ? -1 : min;
    }

    @Test
    void testCase1() {
        assertEquals(3, coinChange(new Integer[]{1, 2, 5}, 11));
    }

    @Test
    void testCase2() {
        assertEquals(-1, coinChange(new Integer[]{2}, 3));
    }

    @Test
    void testCase3() {
        assertEquals(0, coinChange(new Integer[]{1}, 0));
    }
}
