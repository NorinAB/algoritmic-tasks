package com.norin.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/get-maximum-in-generated-array/
//
// You are given an integer n. A 0-indexed integer array nums of length n + 1 is generated in the following way:
//
// nums[0] = 0
// nums[1] = 1
// nums[2 * i] = nums[i] when 2 <= 2 * i <= n
// nums[2 * i + 1] = nums[i] + nums[i + 1] when 2 <= 2 * i + 1 <= n
// Return the maximum integer in the array nums​​​.
//
// Example 1:
// Input: n = 7
// Output: 3
// Explanation: According to the given rules:
//   nums[0] = 0
//   nums[1] = 1
//   nums[(1 * 2) = 2] = nums[1] = 1
//   nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
//   nums[(2 * 2) = 4] = nums[2] = 1
//   nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
//   nums[(3 * 2) = 6] = nums[3] = 2
//   nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
// Hence, nums = [0,1,1,2,1,3,2,3], and the maximum is max(0,1,1,2,1,3,2,3) = 3.
//
// Example 2:
// Input: n = 2
// Output: 1
// Explanation: According to the given rules, nums = [0,1,1]. The maximum is max(0,1,1) = 1.
//
// Example 3:
// Input: n = 3
// Output: 2
// Explanation: According to the given rules, nums = [0,1,1,2]. The maximum is max(0,1,1,2) = 2.
//
// Constraints:
// 0 <= n <= 100
//
// Complexity:
// 	algorithmic O(n)
// 	memory O(n)
class GetMaximumInGeneratedArrayTests {

    public int getMaximumGenerated(int n) {
        if (n < 2) return n;

        int l = n + 1;
        int[] dp = new int[l];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < l; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2];
            } else {
                dp[i] = dp[i / 2 + 1] + dp[i / 2];
            }
        }

        int max = 0;
        for (int i = 0; i < l; i++) {
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    @Test
    void testCase1() {
        Assertions.assertEquals(3, getMaximumGenerated(7));
    }

    @Test
    void testCase2() {
        Assertions.assertEquals(1, getMaximumGenerated(2));
    }

    @Test
    void testCase3() {
        Assertions.assertEquals(2, getMaximumGenerated(3));
    }

    @Test
    void testCase4() {
        Assertions.assertEquals(5, getMaximumGenerated(15));
    }

}
