package com.norin.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://leetcode.com/problems/jump-game/
//
// You are given an integer array nums. You are initially positioned at the array's first index, and each element
// in the array represents your maximum jump length at that position.
// Return true if you can reach the last index, or false otherwise.
//
// Example 1:
// Input: nums = [2,3,1,1,4]
// Output: true
// Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
//
// Example 2:
// Input: nums = [3,2,1,0,4]
// Output: false
// Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
//
// Constraints:
// 1 <= nums.length <= 104
// 0 <= nums[i] <= 105
//
// Complexity:
// 	algorithmic O(?)
// 	memory O(?)
class JumpGameTests {

    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        int power = nums[0];
        int i = 1;
        while (i < nums.length && power > 0) {
            power--;
            power = Math.max(nums[i], power);
            i++;
        }

        return i == nums.length;
    }

    @Test
    void testCase1() {
        Assertions.assertTrue(canJump(new int[]{2, 3, 1, 1, 4}));
    }

    @Test
    void testCase2() {
        Assertions.assertFalse(canJump(new int[]{3, 2, 1, 0, 4}));
    }

    @Test
    void testCase3() {
        Assertions.assertTrue(canJump(new int[]{3, 2, 1, 0}));
    }


}
