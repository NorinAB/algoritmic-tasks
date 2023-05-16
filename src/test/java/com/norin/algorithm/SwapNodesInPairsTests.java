package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// https://leetcode.com/problems/swap-nodes-in-pairs/
// Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying
// the values in the list's nodes (i.e., only nodes themselves may be changed.)
//
// Example 1:
// Input: head = [1,2,3,4]
// Output: [2,1,4,3]
//
// Example 2:
// Input: head = []
// Output: []
//
// Example 3:
// Input: head = [1]
// Output: [1]
//
// Constraints:
// The number of nodes in the list is in the range [0, 100].
// 0 <= Node.val <= 100

// Complexity:
// 	algorithmic O(n)
// 	memory O(1)
class SwapNodesInPairsTests {

    public ListNode swapPairs(ListNode head) {
        ListNode result = head != null && head.next != null ? head.next : head;
        ListNode prev = null;
        while (head != null) {
            ListNode first = head;
            ListNode second = head.next;
            if (second != null) {
                first.next = second.next;
                second.next = first;

                if (prev != null) {
                    prev.next = second;
                }

                prev = first;
            }
            head = first.next;
        }
        return result;
    }

    @Test
    void Test1() {
        ListNode actual = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode expected = new ListNode(2, new ListNode(1, new ListNode(4, new ListNode(3))));
        compare(expected, swapPairs(actual));
    }

    @Test
    void Test2() {
        ListNode actual = new ListNode(1);
        ListNode expected = new ListNode(1);
        compare(expected, swapPairs(actual));
    }

    @Test
    void Test3() {
        ListNode actual = null;
        ListNode expected = null;
        compare(expected, swapPairs(actual));
    }

    @Test
    void Test4() {
        ListNode actual = new ListNode(1, new ListNode(2));
        ListNode expected = new ListNode(2, new ListNode(1));
        compare(expected, swapPairs(actual));
    }

    @Test
    void Test5() {
        ListNode actual = new ListNode(2, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(6, new ListNode(2, new ListNode(2)))))));
        ListNode expected = new ListNode(5, new ListNode(2, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(6, new ListNode(2)))))));
        compare(expected, swapPairs(actual));
    }

    private void compare(ListNode expected, ListNode actual) {
        while (expected != null) {
            assertEquals(expected.val, actual.val);
            expected = expected.next;
            actual = actual.next;
        }
        assertNull(actual);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
