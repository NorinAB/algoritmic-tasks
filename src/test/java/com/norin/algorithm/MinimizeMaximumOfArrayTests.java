package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/minimize-maximum-of-array/
//
// You are given a 0-indexed array nums comprising of n non-negative integers.
//
// In one operation, you must:
// Choose an integer i such that 1 <= i < n and nums[i] > 0.
// Decrease nums[i] by 1.
// Increase nums[i - 1] by 1.
// Return the minimum possible value of the maximum integer of nums after performing any number of operations.
//
// Example 1:
// Input: nums = [3,7,1,6]
// Output: 5
// Explanation:
// One set of optimal operations is as follows:
// 1. Choose i = 1, and nums becomes [4,6,1,6].
// 2. Choose i = 3, and nums becomes [4,6,2,5].
// 3. Choose i = 1, and nums becomes [5,5,2,5].
// The maximum integer of nums is 5. It can be shown that the maximum number cannot be less than 5.
// Therefore, we return 5.
//
// Example 2:
// Input: nums = [10,1]
// Output: 10
// Explanation:
// It is optimal to leave nums as is, and since 10 is the maximum value, we return 10.
//
// Constraints:
// n == nums.length
// 2 <= n <= 105
// 0 <= nums[i] <= 109
//
// Complexity:
// 	algorithmic O(n)
// 	memory O(1)
class MinimizeMaximumOfArrayTests {

    public int minimizeArrayValue(int[] nums) {
        int min = nums[0];
        long free = 0;
        long pastNumberCount = 1;
        for (int i = 1; i < nums.length; i++) {
            long diff = min - nums[i];

            if (diff < 0) {
                if (diff + free > 0) {
                    free += diff;
                } else {
                    diff += free;
                    long addCount = (Math.abs(diff)) / (pastNumberCount + 1);
                    if ((pastNumberCount + 1) * addCount + diff < 0) {
                        addCount++;
                    }
                    min += addCount;
                    diff += (pastNumberCount + 1) * addCount;
                    free = diff;
                }
            } else {
                free += diff;
            }

            pastNumberCount++;
        }
        return min;
    }

    @Test
    void Test1() {
        assertEquals(5, minimizeArrayValue(new int[]{3, 7, 1, 6}));
    }

    @Test
    void Test2() {
        assertEquals(10, minimizeArrayValue(new int[]{10, 1}));
    }

    @Test
    void Test3() {
        assertEquals(5, minimizeArrayValue(new int[]{0, 10}));
    }

    @Test
    void Test4() {
        assertEquals(6, minimizeArrayValue(new int[]{0, 11}));
    }

    @Test
    void Test5() {
        assertEquals(0, minimizeArrayValue(new int[]{0, 0}));
    }

    @Test
    void Test6() {
        assertEquals(1, minimizeArrayValue(new int[]{0, 0, 0, 0, 5}));
    }

    @Test
    void Test7() {
        assertEquals(16, minimizeArrayValue(new int[]{13,13,20,0,8,9,9}));
    }

}
