package com.norin.algorithm;

import com.norin.algorithm.InvertBinaryTreeTests.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

// https://leetcode.com/problems/validate-binary-search-tree/

// Given the root of a binary tree, determine if it is a valid binary search tree (BST).
//
// A valid BST is defined as follows:
//   The left subtree of a node contains only nodes with keys less than the node's key.
//   The right subtree of a node contains only nodes with keys greater than the node's key.
//   Both the left and right subtrees must also be binary search trees.
//
// Example 1:
// Input: root = [2,1,3]
// Output: true
//
// Example 2:
// Input: root = [5,1,4,null,null,3,6]
// Output: false
// Explanation: The root node's value is 5 but its right child's value is 4.
//
// Example 3:
// Input: coins = [1], amount = 0
// Output: 0
//
// Constraints:
//
// The number of nodes in the tree is in the range [1, 104].
// -231 <= Node.val <= 231 - 1
//
// Complexity:
// 	algorithmic O(n)
// 	memory O(h - height of tree)
class ValidateBSTTests {

    public boolean isValidBST(TreeNode root) {
        return isValidNode(root.left, null, root.val)
                && isValidNode(root.right, root.val, null);
    }

    public boolean isValidNode(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;

        return (min == null || node.val > min) && (max == null || node.val < max)
                && isValidNode(node.left, min, node.val)
                && isValidNode(node.right, node.val, max);
    }

    @Test
    void testCase1() {
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        assertTrue(isValidBST(root));
    }

    @Test
    void testCase2() {
        TreeNode root = new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)));

        assertFalse(isValidBST(root));
    }


}
