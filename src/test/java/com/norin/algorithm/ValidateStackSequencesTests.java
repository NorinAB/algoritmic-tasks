package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// https://leetcode.com/problems/validate-stack-sequences/
//
// Given two integer arrays pushed and popped each with distinct values, return true if this could have been the result
// of a sequence of push and pop operations on an initially empty stack, or false otherwise.
//
// Example 1:
// Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
// Output: true
// Explanation: We might do the following sequence:
// push(1), push(2), push(3), push(4),
// pop() -> 4,
// push(5),
// pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
//
// Example 2:
// Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
// Output: false
// Explanation: 1 cannot be popped before 2.
//
// Constraints:
// 1 <= pushed.length <= 1000
// 0 <= pushed[i] <= 1000
// All the elements of pushed are unique.
// popped.length == pushed.length
// popped is a permutation of pushed.
//
// Complexity:
// 	algorithmic O(n)
// 	memory O(n)
class ValidateStackSequencesTests {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();

        int pIndex = 0;
        for (int n : pushed) {
            stack.push(n);
            while (pIndex < pushed.length && !stack.isEmpty() && popped[pIndex] == stack.peek()) {
                pIndex++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    @Test
    void testCase1() {
        assertTrue(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
        assertTrue(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{1, 5, 4, 3, 2}));
        assertTrue(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{1, 3, 5, 4, 2}));
    }

    @Test
    void testCase2() {
        assertFalse(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
    }

    @Test
    void testCase3() {
        assertTrue(validateStackSequences(new int[]{1}, new int[]{1}));
    }

    @Test
    void testCase4() {
        assertTrue(validateStackSequences(new int[]{1, 2}, new int[]{2, 1}));
    }

    @Test
    void testCase5() {
        assertTrue(validateStackSequences(new int[]{1, 2}, new int[]{1, 2}));
    }

}
