package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/number-of-closed-islands/
// Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s
// and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
// Return the number of closed islands.
//
// Example 1:
// Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
// Output: 2
// Explanation:
// Islands in gray are closed because they are completely surrounded by water (group of 1s).
//
// Example 2:
// Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
// Output: 1
//
// Example 3:
//
// Input: grid = [[1,1,1,1,1,1,1],
//                [1,0,0,0,0,0,1],
//                [1,0,1,1,1,0,1],
//                [1,0,1,0,1,0,1],
//                [1,0,1,1,1,0,1],
//                [1,0,0,0,0,0,1],
//                [1,1,1,1,1,1,1]]
// Output: 2
// Constraints:
//
// 1 <= grid.length, grid[0].length <= 100
// 0 <= grid[i][j] <=1
//
// Complexity:
// 	algorithmic O(n*m)
// 	memory O(m*n)
class NumberOfClosedIslandsTests {

    public int closedIsland(int[][] grid) {
        int cnt = 0;
        Boolean[][] visited = new Boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (visited[i][j] == null && grid[i][j] == 0 && isWaterOrIsolatedIsland(grid, visited, i, j)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    boolean isWaterOrIsolatedIsland(int[][] grid, Boolean[][] visited, int i, int j) {
        if (visited[i][j] != null) {
            return visited[i][j];
        }

        if ((i < 0) || (j < 0) || (i > grid.length - 1) || (j > grid[i].length - 1)) {
            visited[i][j] = false;
            return false;
        }
        if (grid[i][j] == 0) {
            if ((i == 0) || (j == 0) || (i == grid.length - 1) || (j == grid[i].length - 1)) {
                visited[i][j] = false;
                return false;
            }

            grid[i][j] = -1;
            boolean isIsolated = isWaterOrIsolatedIsland(grid, visited, i + 1, j)
                    && isWaterOrIsolatedIsland(grid, visited, i - 1, j)
                    && isWaterOrIsolatedIsland(grid, visited, i, j + 1)
                    && isWaterOrIsolatedIsland(grid, visited, i, j - 1);
            grid[i][j] = 0;

            visited[i][j] = isIsolated;
            return isIsolated;
        }
        visited[i][j] = true;
        return true;
    }

    @Test
    void testCase7() {
        assertEquals(1, closedIsland(new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        }));
    }

    @Test
    void testCase8() {
        assertEquals(0, closedIsland(new int[][]{
                {0, 0, 0},
                {1, 0, 1},
                {0, 1, 0}
        }));
    }

    @Test
    void testCase1() {
        assertEquals(2, closedIsland(new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        }));
    }

    @Test
    void testCase2() {
        assertEquals(1, closedIsland(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0},
                {0, 0, 0}
        }));
    }

    @Test
    void testCase3() {
        assertEquals(2, closedIsland(new int[][]{
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        }));
    }

    @Test
    void testCase4() {
        assertEquals(0, closedIsland(new int[][]{
                {0}
        }));
    }

    @Test
    void testCase5() {
        assertEquals(0, closedIsland(new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 0}
        }));
    }

    @Test
    void testCase6() {
        assertEquals(1, closedIsland(new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        }));
    }

    @Test
    void testCase9() {
        assertEquals(0, closedIsland(new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 0, 0}
        }));
    }

    @Test
    void testCase10() {
        assertEquals(0, closedIsland(new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {0, 1, 0}
        }));
    }

    @Test
    void testCase11() {
        assertEquals(0, closedIsland(new int[][]{
                {0, 1, 0},
                {1, 0, 0},
                {0, 1, 0}
        }));
    }

    @Test
    void testCase12() {
        assertEquals(5, closedIsland(new int[][]{
                {0, 0, 1, 1, 0, 1, 0, 0, 1, 0},
                {1, 1, 0, 1, 1, 0, 1, 1, 1, 0},
                {1, 0, 1, 1, 1, 0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}
        }));
    }
}
