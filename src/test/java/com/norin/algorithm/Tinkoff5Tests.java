package com.norin.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

// Есть N комнат, пронумерованных от 0 до N - 1. Все комнаты заперты, кроме комнаты 0.
// Ваша цель - посетить все комнаты, используя найденные ключи в других комнатах.
// Когда вы посещаете комнату, вы можете найти в ней набор различных ключей.
// На каждом ключе есть номер, обозначающий, какую комнату он открывает.
// Вы можете взять все эти ключи с собой, чтобы открыть другие комнаты.
// Дан массив A, где A[i] — это набор ключей, которые вы можете получить, если посетите A[i].
// Верните true, если вы можете посетить все комнаты или false в противном случае.
// [[1],[2],[3],[]] // true
// [[2,4],[1,2,0],[3],[1],[3]] // true
// [[1],[0]] // true
// [[1,3],[3,0,1],[2],[0]] // false
// [[],[1],[2],[3]] // false
class Tinkoff5Tests {

    public static boolean process(int[][] rooms) {
        boolean[] visited = new boolean[rooms.length];
        Arrays.fill(visited, false);
        Stack<Integer> stack = new Stack<>();
        visited[0] = true;
        stack.push(0);
        while (!stack.isEmpty()) {
            int nextRoom = stack.pop();
            for (int i = 0; i < rooms[nextRoom].length; i++) {
                if (!visited[rooms[nextRoom][i]]) {
                    stack.push(rooms[nextRoom][i]);
                    visited[rooms[nextRoom][i]] = true;
                }
            }
        }
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    @Test
    void testCase1() {
        Assertions.assertTrue(process(new int[][]{{1}, {2}, {3}, {}}));
    }

    @Test
    void testCase3() {
        Assertions.assertFalse(process(new int[][]{{1, 3}, {3, 0, 1}, {2}, {0}}));
    }

    @Test
    void testCase2() {
        Assertions.assertTrue(process(new int[][]{{2, 4}, {1, 2, 0}, {3}, {1}, {3}}));
    }

    @Test
    void testCase4() {
        Assertions.assertTrue(process(new int[][]{{1}, {0}}));
    }

    @Test
    void testCase5() {
        Assertions.assertFalse(process(new int[][]{{}, {1}, {2}, {3}}));
    }

}
