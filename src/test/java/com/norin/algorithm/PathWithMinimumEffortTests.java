package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/path-with-minimum-effort/
// You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
// where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
// and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
// You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
// A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
// Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
//
// Constraints:
// rows == heights.length
// columns == heights[i].length
// 1 <= rows, columns <= 100
// 1 <= heights[i][j] <= 106
//
// Complexity:
// 	algorithmic O(n*m)
// 	memory O(n*m)
class PathWithMinimumEffortTests {

    class Point {
        int x;
        int y;
        int l;

        public Point(int x, int y, int l) {
            this.x = x;
            this.y = y;
            this.l = l;
        }
    }

    public int minimumEffortPath(int[][] heights) {
        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.l));
        queue.add(new Point(0, 0, 0));
        BitSet visited = new BitSet();

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            System.out.println(" x = " + p.x + " y = " + p.y + " l = " + p.l);
            if (p.x == heights.length - 1 && p.y == heights[0].length - 1) {
                return p.l;
            }

            if (p.x - 1 >= 0 && !visited.get((p.x - 1) * 1000 + p.y)) {
                queue.add(new Point(p.x - 1, p.y, Math.max(p.l, Math.abs(heights[p.x][p.y] - heights[p.x - 1][p.y]))));
            }
            if (p.x + 1 < heights.length && !visited.get((p.x + 1) * 1000 + p.y)) {
                queue.add(new Point(p.x + 1, p.y, Math.max(p.l, Math.abs(heights[p.x][p.y] - heights[p.x + 1][p.y]))));
            }
            if (p.y - 1 >= 0 && !visited.get((p.x) * 1000 + p.y - 1)) {
                queue.add(new Point(p.x, p.y - 1, Math.max(p.l, Math.abs(heights[p.x][p.y] - heights[p.x][p.y - 1]))));
            }
            if (p.y + 1 < heights[0].length && !visited.get((p.x) * 1000 + p.y + 1)) {
                queue.add(new Point(p.x, p.y + 1, Math.max(p.l, Math.abs(heights[p.x][p.y] - heights[p.x][p.y + 1]))));
            }
            visited.set(p.x * 1000 + p.y);
        }

        return -1;
    }

    @Test
    void Test1() {
        assertEquals(2, minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}}));
    }

    @Test
    void Test2() {
        assertEquals(1, minimumEffortPath(new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}}));
    }

    @Test
    void Test3() {
        assertEquals(0, minimumEffortPath(
                        new int[][]{{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}}
                )
        );
    }

    @Test
    void Test4() {
        assertEquals(9, minimumEffortPath(new int[][]{{1, 10, 6, 7, 9, 10, 4, 9}})
        );
    }

    @Test
    void Test5() {
        assertEquals(6, minimumEffortPath(new int[][]{{10, 8}, {10, 8}, {1, 2}, {10, 3}, {1, 3}, {6, 3}, {5, 2}})
        );
    }

    @Test
    void Test6() {
        assertEquals(5, minimumEffortPath(new int[][]{{4,3,4,10,5,5,9,2},{10,8,2,10,9,7,5,6},{5,8,10,10,10,7,4,2},{5,1,3,1,1,3,1,9},{6,4,10,6,10,9,4,6}})
        );
    }
}
