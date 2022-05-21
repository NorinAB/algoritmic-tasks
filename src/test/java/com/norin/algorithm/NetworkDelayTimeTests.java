package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/network-delay-time/
// You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as
// directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time
// it takes for a signal to travel from source to target.
// We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal.
// If it is impossible for all the n nodes to receive the signal, return -1.
//
// Example 1:
// Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
// Output: 2
//
// Example 2
// Input: times = [[1,2,1]], n = 2, k = 1
// Output: 1
//
// Example 3
// Input: times = [[1,2,1]], n = 2, k = 2
// Output: -1
//
// 1 <= k <= n <= 100
// 1 <= times.length <= 6000
// times[i].length == 3
// 1 <= ui, vi <= n
// ui != vi
// 0 <= wi <= 100
// All the pairs (ui, vi) are unique. (i.e., no multiple edges.)

// Complexity:
// 	algorithmic O(?)
// 	memory O(?)
class NetworkDelayTimeTests {

    class Step {
        int length;
        int to;

        public Step(int to, int length) {
            this.length = length;
            this.to = to;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        // init data
        BitSet visited = new BitSet(n+1);
        int[] shortestLength = new int[n+1];
        Arrays.fill(shortestLength, Integer.MAX_VALUE);
        shortestLength[0] = 0;
        Map<Integer, List<Step>> stepsFromNode = new HashMap<>();
        for (int[] rout : times) {
            int from = rout[0];
            if (!stepsFromNode.containsKey(from)) {
                stepsFromNode.put(from, new LinkedList<>());
            }
            stepsFromNode.get(from).add(new Step(rout[1], rout[2]));
        }

        // start point
        int from = k;
        shortestLength[k] = 0;
        PriorityQueue<Step> nextNodeSteps = new PriorityQueue<>(Comparator.comparingInt(o -> o.length));
        do {
            visited.set(from);
            if (stepsFromNode.containsKey(from)) {
                for (Step step : stepsFromNode.get(from)) {
                    if (shortestLength[step.to] > shortestLength[from] + step.length) {
                        shortestLength[step.to] = shortestLength[from] + step.length;
                    }
                    if (!visited.get(step.to)) {
                        nextNodeSteps.add(new Step(step.to, shortestLength[step.to]));
                    }
                }
            }

            while (!nextNodeSteps.isEmpty() && visited.get(nextNodeSteps.peek().to)) {
                nextNodeSteps.poll();
            }
            if (!nextNodeSteps.isEmpty()) {
                from = nextNodeSteps.peek().to;
            }
        }
        while (!nextNodeSteps.isEmpty());

        int max = getMax(shortestLength);
        return max == Integer.MAX_VALUE ? -1 : max;
    }

    private int getMax(int[] paths) {
        int max = -1;
        for (Integer l : paths) {
            if (l > max) {
                max = l;
            }
        }
        return max;
    }

    @Test
    void Test1() {
        assertEquals(2, networkDelayTime(new int[][] {{2,1,1}, {2,3,1}, {3,4,1}}, 4, 2));
    }

    @Test
    void Test2() {
        assertEquals(1, networkDelayTime(new int[][] {{1,2,1}}, 2, 1));
    }

    @Test
    void Test3() {
        assertEquals(-1, networkDelayTime(new int[][] {{1,2,1}}, 2, 2));
    }

}
