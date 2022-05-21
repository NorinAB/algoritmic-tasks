package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/group-anagrams/
// Given an array of strings strs, group the anagrams together. You can return the answer in any order.
// An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
//
// Constraints:
// 1 <= strs.length <= 104
// 0 <= strs[i].length <= 100
// strs[i] consists of lowercase English letters.
//
// Complexity:
// 	algorithmic O(n*m)
// 	memory O(n)
class GroupAnagramsTests {

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> result = new HashMap<>();
        for (String s : strs) {
            String key = getKey(s);
            result.putIfAbsent(key, new LinkedList<>());
            result.get(key).add(s);
        }

        return new ArrayList<>(result.values());
    }

    private String getKey(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    @Test
    void Test1() {
        List<List<String>> lists = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        assertEquals(3, lists.size());
        assertEquals(3, lists.get(0).size());
        assertEquals(1, lists.get(1).size());
        assertEquals(2, lists.get(2).size());
    }

    @Test
    void Test2() {
        List<List<String>> lists = groupAnagrams(new String[]{"a"});
        assertEquals(1, lists.size());
        assertEquals(1, lists.get(0).size());
        assertEquals("a", lists.get(0).get(0));
    }
}
