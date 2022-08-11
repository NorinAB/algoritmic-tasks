package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Ваша задача: посчитать максимальное количество пользователей, одновременно смотревших игровой стрим.
//
// Каждый пользователь подключался к стриму в какой-то момент времени t_in
// и отключался в момент времени t_out – время измеряется в секундах (от 0 до 10^9).
// У каждого пользователя это время своё.
//
// Вам дан массив (неупорядоченный) из пар (t_in, t_out) – длина массива от 0 до 10^6.
// Требуется вывести число – максимальное количество пользователей, которые одновременно смотрели стрим.
//
// Примеры
// [ ] -> 0
// {{1, 5}, {5, 10}} -> 1
// [{1, 5}, {0, 1}, {4, 5}] -> 2
// [{1, 10}, {5, 6}, {2, 3}, {7, 8}] -> 2
// [{1, 2}, {1, 10}, {4, 9}, {8, 15}, {5, 6}, {8, 16}] -> 4
class Tinkoff2Tests {

    static class Event {
        long time;
        int dir;

        public Event(long time, int dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    public int getMax(int[][] times) {
        if (times.length == 0) return 0;
        PriorityQueue<Event> queue = new PriorityQueue<>((o1, o2) -> (int) (o1.time - o2.time));

        for (int[] e : times) {
            queue.add(new Event(e[0], 1));
            queue.add(new Event(e[1], -1));
        }

        int max = 0;
        int v = 0;
        long t = 0;
        while (!queue.isEmpty()) {
            if (t != queue.peek().time) {
                if (v > max) max = v;
                t = queue.peek().time;
            }
            v += queue.poll().dir;
        }

        return max;
    }

    @Test
    void testCase1() {
        assertEquals(1, getMax(new int[][]{{1, 5}, {5, 10}}));
    }

    @Test
    void testCase2() {
        assertEquals(2, getMax(new int[][]{{1, 5}, {0, 1}, {4, 5}}));
    }

    @Test
    void testCase3() {
        assertEquals(0, getMax(new int[][]{}));
    }

    @Test
    void testCase4() {
        assertEquals(5, getMax(new int[][]{{0, 10}, {1, 9}, {2, 8}, {3, 7}, {4, 6}, {5, 5}}));
    }

    @Test
    void testCase5() {
        assertEquals(2, getMax(new int[][]{{1, 10}, {5, 6}, {2, 3}, {7, 8}}));
    }

    @Test
    void testCase6() {
        assertEquals(4, getMax(new int[][]{{1, 2}, {1, 10}, {4, 9}, {8, 15}, {5, 6}, {8, 16}}));
    }

}
