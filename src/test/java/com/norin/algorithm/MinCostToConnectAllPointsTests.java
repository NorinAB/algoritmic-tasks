package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/min-cost-to-connect-all-points/
// You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
//
// The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
//
// Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
// Constraints:
//
// 1 <= points.length <= 1000
// -10^6 <= xi, yi <= 10^6
// All pairs (xi, yi) are distinct.
class MinCostToConnectAllPointsTests {

    public int minCostConnectPoints(int[][] points) {
        if (points.length == 1) return 0;

        List<Arc> queue = new ArrayList<>();
        for (int from = 0; from < points.length; from++) {
            for (int to = from + 1; to < points.length; to++) {
                queue.add(
                        new Arc(from,
                                to,
                                Math.abs(points[from][0] - points[to][0]) + Math.abs(points[from][1] - points[to][1]))
                );
            }
        }

        Collections.sort(queue);

        int sum = 0;
        HashSet<Integer> visited = new HashSet<>();
        visited.add(queue.get(0).from);
        visited.add(queue.get(0).to);
        sum += queue.get(0).length;

        while (visited.size() < points.length) {
            for (Arc arc : queue) {
                if (visited.contains(arc.from) ^ visited.contains(arc.to)) {
                    visited.add(arc.from);
                    visited.add(arc.to);
                    sum += arc.length;
                    break;
                }
            }
        }

        return sum;
    }

    class Arc implements Comparable<Arc> {
        int from;
        int to;
        int length;

        public Arc(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public int compareTo(Arc o) {
            return this.length - o.length;
        }
    }

    @Test
    void testCase1() {
        assertEquals(20, minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
    }

    @Test
    void testCase2() {
        assertEquals(18, minCostConnectPoints(new int[][]{{3, 12}, {-2, 5}, {-4, 1}}));
    }

    @Test
    void testCase3() {
        assertEquals(4, minCostConnectPoints(new int[][]{{0, 0}, {1, 1}, {1, 0}, {-1, 1}}));
    }

    @Test
    void testCase4() {
        assertEquals(1, minCostConnectPoints(new int[][]{{0, 0}, {1, 0}}));
    }

    @Test
    void testCase5() {
        assertEquals(0, minCostConnectPoints(new int[][]{{0, 0}}));
    }

    @Test
    void testCase6() {
        assertEquals(53, minCostConnectPoints(new int[][]{{2, -3}, {-17, -8}, {13, 8}, {-17, -15}}));
    }

    @Test
    void testCase7() {
        assertEquals(4, minCostConnectPoints(new int[][]{{-1, 0}, {0, 0}, {1, 0}, {0, 1}, {0, -1}}));
    }
}
