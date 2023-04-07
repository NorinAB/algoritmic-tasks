package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/number-of-enclaves/
// You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
// A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
// Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
//
// Example 1:
// Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
// Output: 3
// Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
//
// Example 2:
// Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
// Output: 0
// Explanation: All 1s are either on the boundary or can reach the boundary.
//
// Constraints:
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 500
// grid[i][j] is either 0 or 1.
//
// Complexity:
// 	algorithmic O(n*m)
// 	memory O(m*n)
class NumberOfEnclavesTests {

    public int numEnclaves(int[][] grid) {
        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    int num = getNumEnclaves(visited, grid, i, j);
                    count += num == -1 ? 0 : num;
                }
            }
        }

        return count;
    }

    private int getNumEnclaves(boolean[][] visited, int[][] grid, int i, int j) {
        if (visited[i][j]) return 0;

        if (grid[i][j] == 0) {
            return 0;
        }

        if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[i].length - 1) {
            return -1;
        }

        visited[i][j] = true;
        int b = getNumEnclaves(visited, grid, i - 1, j);
        int t = getNumEnclaves(visited, grid, i + 1, j);
        int l = getNumEnclaves(visited, grid, i, j - 1);
        int r = getNumEnclaves(visited, grid, i, j + 1);
        if (b == -1 || t == -1 || l == -1 || r == -1) {
            return -1;
        }

        return 1 + b + t + l + r;
    }

    @Test
    void testCase1() {
        assertEquals(11, numEnclaves(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 1}
        }));
    }

    @Test
    void testCase2() {
        assertEquals(1, numEnclaves(new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1},
                {1, 1, 1}
        }));
    }

    @Test
    void testCase3() {
        assertEquals(17, numEnclaves(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0}
        }));
    }

    @Test
    void testCase4() {
        assertEquals(0, numEnclaves(new int[][]{
                {1}
        }));
    }

    @Test
    void testCase5() {
        assertEquals(0, numEnclaves(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 1}
        }));
    }

    @Test
    void testCase6() {
        assertEquals(1, numEnclaves(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        }));
    }

    @Test
    void testCase7() {
        assertEquals(1, numEnclaves(new int[][]{
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
        }));
    }

    @Test
    void testCase8() {
        assertEquals(0, numEnclaves(new int[][]{
                {1, 1, 1},
                {0, 1, 0},
                {1, 0, 1}
        }));
    }

    @Test
    void testCase9() {
        assertEquals(0, numEnclaves(new int[][]{
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        }));
    }

    @Test
    void testCase10() {
        assertEquals(0, numEnclaves(new int[][]{
                {1, 0, 1},
                {1, 1, 0},
                {1, 0, 1}
        }));
    }

    @Test
    void testCase11() {
        assertEquals(0, numEnclaves(new int[][]{
                {1, 0, 1},
                {0, 1, 1},
                {1, 0, 1}
        }));
    }

//    @Test
//    void testCase12() {
//        assertEquals(5, numEnclaves(new int[][]{
//                {0, 0, 1, 1, 0, 1, 0, 0, 1, 0},
//                {1, 1, 0, 1, 1, 0, 1, 1, 1, 0},
//                {1, 0, 1, 1, 1, 0, 0, 1, 1, 0},
//                {0, 1, 1, 0, 0, 0, 0, 1, 0, 1},
//                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
//                {0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
//                {1, 0, 1, 0, 1, 1, 0, 0, 0, 1},
//                {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
//                {1, 1, 1, 0, 0, 1, 0, 1, 0, 1},
//                {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}
//        }));
//    }

    @Test
    void testCase13() {
        assertEquals(25, numEnclaves(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0}
        }));
    }

    @Test
    void testCase14() {
        assertEquals(3, numEnclaves(new int[][]{
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        }));
    }
}
