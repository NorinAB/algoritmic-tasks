package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/largest-color-value-in-a-directed-graph/
//
// There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
// You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node
// in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a
// directed edge from node aj to node bj.
//
// A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge
// from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most
// frequently occurring color along that path.
// Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
//
//
// Example 1:
// Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
// Output: 3
// Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
//
// Example 2:
// Input: colors = "a", edges = [[0,0]]
// Output: -1
// Explanation: There is a cycle from 0 to 0.
//
// Constraints:
// n == colors.length
// m == edges.length
// 1 <= n <= 105
// 0 <= m <= 105
// colors consists of lowercase English letters.
// 0 <= aj, bj < n
//
// Complexity:
// 	algorithmic O(?)
// 	memory O(?)
class LargestColorValueInADirectedGraphTests {

    public int largestPathValue(String colors, int[][] edges) {
        int max = 0;
        Set<Integer> parents = new HashSet<>();

        Map<Integer, Map<Character, Integer>> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            parents.add(edges[i][0]);
            Map<Character, Integer> parentInfo = map.computeIfAbsent(edges[i][0], (key) -> new HashMap<>());
            if (parentInfo.isEmpty()) {
                parentInfo.put(colors.charAt(edges[i][0]), 1);
            }

            if (parents.contains(edges[i][1])) {
                return -1;
            }
            Map<Character, Integer> childInfo = map.computeIfAbsent(edges[i][1], (key) -> new HashMap<>());

            for(Character c : parentInfo.keySet()) {
                childInfo.put(c, parentInfo.get(c));
            }

            Character c = colors.charAt(edges[i][1]);
            int cnt = childInfo.getOrDefault(c, 0) + 1;
            max = Math.max(max, cnt);
            childInfo.put(c, cnt);
        }

        return max;
    }

    @Test
    void testCase1() {
        assertEquals(3, largestPathValue("abaca", new int[][]{{0, 1}, {0, 2}, {2, 3}, {3, 4}}));
    }

    @Test
    void testCase2() {
        assertEquals(-1, largestPathValue("a", new int[][]{{0, 0}}));
    }
}
