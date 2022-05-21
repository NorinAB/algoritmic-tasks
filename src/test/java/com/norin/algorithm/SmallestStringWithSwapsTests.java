package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/smallest-string-with-swaps/
// You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
// You can swap the characters at any pair of indices in the given pairs any number of times.
// Return the lexicographically smallest string that s can be changed to after using the swaps.
//
// Constraints:
// 1 <= s.length <= 10^5
// 0 <= pairs.length <= 10^5
// 0 <= pairs[i][0], pairs[i][1] < s.length
// s only contains lower case English letters.
class SmallestStringWithSwapsTests {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) return s;

        char[] chars = s.toCharArray();
        TreeSet<Integer> achievable = new TreeSet<>();
        for (List<Integer> pair : pairs) {
            achievable.add(pair.get(0));
            achievable.add(pair.get(1));
        }

        while (!achievable.isEmpty()) {
            TreeSet<Integer> connected = new TreeSet<>();
            connected.add(achievable.pollFirst());

            int connectedSize;
            do {
                connectedSize = connected.size();
                Iterator<List<Integer>> iterator = pairs.iterator();
                while (iterator.hasNext()) {
                    List<Integer> pair = iterator.next();
                    if (connected.contains(pair.get(0))) {
                        connected.add(pair.get(1));
                        iterator.remove();
                    } else if (connected.contains(pair.get(1))) {
                        connected.add(pair.get(0));
                        iterator.remove();
                    }
                }
            }
            while (connectedSize != connected.size());
            achievable.removeAll(connected);

            List<Character> connectedChars = new ArrayList<>();
            for (Integer i : connected) {
                connectedChars.add(chars[i]);
            }
            connectedChars.sort(Comparator.comparingInt(o -> o));

            int charIndex = 0;
            for (Integer i : connected) {
                chars[i] = connectedChars.get(charIndex++);
            }
        }
        return new String(chars);
    }

    @Test
    void testCase1() {
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> pair = new ArrayList<>();
        pair.add(0);
        pair.add(3);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(1);
        pair.add(2);
        pairs.add(pair);
        assertEquals("bacd", smallestStringWithSwaps("dcab",pairs));
    }

    @Test
    void testCase2() {
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> pair = new ArrayList<>();
        pair.add(0);
        pair.add(3);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(1);
        pair.add(2);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(0);
        pair.add(2);
        pairs.add(pair);
        assertEquals("abcd", smallestStringWithSwaps("dcab", pairs));
    }

    @Test
    void testCase3() {
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> pair = new ArrayList<>();
        pair.add(0);
        pair.add(1);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(1);
        pair.add(2);
        pairs.add(pair);
        assertEquals("abc", smallestStringWithSwaps("cba", pairs));
    }

    @Test
    void testCase4() {
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> pair = new ArrayList<>();
        pair.add(5);
        pair.add(3);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(3);
        pair.add(0);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(5);
        pair.add(1);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(1);
        pair.add(1);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(1);
        pair.add(5);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(3);
        pair.add(0);
        pairs.add(pair);
        pair = new ArrayList<>();
        pair.add(0);
        pair.add(2);
        pairs.add(pair);
        assertEquals("lpqqmwm", smallestStringWithSwaps("pwqlmqm", pairs));

    }

}

// best solution
//    int n, p[];
//    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
//        n = s.length();
//        p = new int[n];
//        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
//        for (int i = 0; i < n; i++) p[i] = i;
//        for (List<Integer> l : pairs) {
//            union(l.get(0), l.get(1));
//        }
//        for (int i = 0; i < n; i++) {
//            map.computeIfAbsent(find(i), k -> new PriorityQueue<>((a, b) -> a - b));
//            map.get(p[i]).offer(s.charAt(i));
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < n; i++) {
//            sb.append(map.get(p[i]).poll());
//        }
//        return sb.toString();
//    }
//
//    private int find(int i) {
//        if (i != p[i]) p[i] = find(p[i]);
//        return p[i];
//    }
//
//    private void union(int i, int j) {
//        int pi = find(i), pj = find(j);
//        if (pi > pj) {
//            p[pi] = pj;  // p[pi] other than p[i];
//        } else {
//            p[pj] = pi;
//        }
//    }
