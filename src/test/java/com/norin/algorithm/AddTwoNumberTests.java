package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/add-two-numbers/
// You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
// Example 1:
// Input: l1 = [2,4,3], l2 = [5,6,4]
// Output: [7,0,8]
// Explanation: 342 + 465 = 807.
//
// Example 2:
// Input: l1 = [0], l2 = [0]
// Output: [0]
//
// Example 3:
// Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
// Output: [8,9,9,9,0,0,0,1]
//
// Constraints:
// The number of nodes in each linked list is in the range [1, 100].
// 0 <= Node.val <= 9
// It is guaranteed that the list represents a number that does not have leading zeros.

// Complexity:
// 	algorithmic O(n)
// 	memory O(n)
class AddTwoNumberTests {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode prev = null;
        boolean hasPassedTen = false;

        do {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            ListNode addedNode = new ListNode(
                    val1 + val2 + (hasPassedTen ? 1 : 0)
            );
            hasPassedTen = addedNode.val >= 10;
            if (hasPassedTen) {
                addedNode.val -= 10;
            }

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

            if (prev != null) {
                prev.next = addedNode;
            } else {
                result = addedNode;
            }
            prev = addedNode;

        } while (l1 != null || l2 != null);

        if (hasPassedTen) {
            prev.next = new ListNode(1);
        }

        return result;
    }

    @Test
    void Test1() {
        ListNode node342 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode node465 = new ListNode(5, new ListNode(6, new ListNode(4)));
        assertEquals(807, extractNumber(addTwoNumbers(node342, node465)));
    }

    @Test
    void Test2() {
        ListNode node0 = new ListNode(0);
        assertEquals(0, extractNumber(addTwoNumbers(node0, node0)));
    }

    @Test
    void Test3() {
        ListNode node9999999 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        ListNode node9999 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        assertEquals(10009998, extractNumber(addTwoNumbers(node9999999, node9999)));
    }

    private int extractNumber(ListNode node) {
        int result = 0;
        int pow = 0;
        while (node != null) {
            result += node.val * (int) Math.pow(10, pow++);
            node = node.next;
        }
        return result;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
