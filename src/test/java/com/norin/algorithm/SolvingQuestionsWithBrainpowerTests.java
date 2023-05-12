package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/solving-questions-with-brainpower/

// You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].
// The array describes the questions of an exam, where you have to process the questions in order
// (i.e., starting from question 0) and make a decision whether to solve or skip each question.
// Solving question i will earn you pointsi points, but you will be unable to solve each of the next
// brainpower[i] questions. If you skip question i, you get to make the decision on the next question.
// For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
// If question 0 is solved, you will earn 3 points, but you will be unable to solve questions 1 and 2.
// If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but you will be unable to solve questions 2 and 3.
// Return the maximum points you can earn for the exam.
//
// Example 1:
// Input: questions = [[3,2],[4,3],[4,4],[2,5]]
// Output: 5
// Explanation: The maximum points can be earned by solving questions 0 and 3.
// - Solve question 0: Earn 3 points, will be unable to solve the next 2 questions
// - Unable to solve questions 1 and 2
// - Solve question 3: Earn 2 points
// Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more points.
//
// Example 2:
// Input: questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
// Output: 7
// Explanation: The maximum points can be earned by solving questions 1 and 4.
// - Skip question 0
// - Solve question 1: Earn 2 points, will be unable to solve the next 2 questions
// - Unable to solve questions 2 and 3
// - Solve question 4: Earn 5 points
// Total points earned: 2 + 5 = 7. There is no other way to earn 7 or more points.
//
// Constraints:
// 1 <= questions.length <= 105
// questions[i].length == 2
// 1 <= pointsi, brainpoweri <= 105
//
// Complexity:
// 	algorithmic O(n)
// 	memory O(n)
class SolvingQuestionsWithBrainpowerTests {

    public long mostPoints(int[][] questions) {
        long[] dp = new long[questions.length];
        for (int i = questions.length - 1; i >= 0; i--) {
            int nextMax = i + questions[i][1] + 1;
            long possibleMax = questions[i][0] + (nextMax < questions.length ? dp[nextMax] : 0);
            if (i < questions.length - 1) {
                dp[i] = Math.max(possibleMax, dp[i + 1]);
            } else {
                dp[i] = possibleMax;
            }
        }
        return dp[0];
    }

    @Test
    void testCase1() {
        assertEquals(5, mostPoints(new int[][]{{3, 2}, {4, 3}, {4, 4,}, {2, 5}}));
    }

    @Test
    void testCase2() {
        assertEquals(7, mostPoints(new int[][]{{1, 1}, {2, 2}, {3, 3,}, {4, 4}, {5, 5}}));
    }

    @Test
    void testCase3() {
        assertEquals(30, mostPoints(new int[][]{{10, 1}, {10, 1}, {10, 1}, {10, 1}, {10, 1}}));
    }

    @Test
    void testCase4() {
        int[][] arr = new int[100000][2];
        for (int i = 0; i < 100000; i++) {
            arr[i][0] = 100000;
            arr[i][1] = 1;
        }

        assertEquals(5000000000L, mostPoints(arr));
    }

}
