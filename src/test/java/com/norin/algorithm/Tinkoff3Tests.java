package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Дана строка, найти длину наибольшей подстроки, состоящей из уникальных символов.
//
// Пример: "abcdabc" → "abcd" -> 4
class Tinkoff3Tests {

    public int getMax(String str) {
        char[] arr = str.toCharArray();
        if (str.length() == 0) return 0;
        int start = 0;
        int end = 0;
        Set<Character> set = new HashSet<>();
        while (end < arr.length) {
            set.add(arr[end]);
            if (set.size() != end - start + 1) {
                if (arr[start] != arr[end]) {
                    set.remove(arr[start]);
                }
                start++;
            }
            end++;
        }
        return end - start;
    }

    @Test
    void testCase1() {
        assertEquals(4, getMax("abcdabc"));
    }

    @Test
    void testCase2() {
        assertEquals(5, getMax("tmmzuxt"));
    }

}
