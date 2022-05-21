package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/invert-binary-tree/
// Given the root of a binary tree, invert the tree, and return its root.
//
// Constraints:
// The number of nodes in the tree is in the range [0, 100].
// -100 <= Node.val <= 100
//
// Complexity:
// 	algorithmic O(n)
// 	memory O(1)
class InvertBinaryTreeTests {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode invertTree(TreeNode root) {
        shift(root);
        return root;
    }

    private void shift(TreeNode root) {
        if (root == null) return;
        shift(root.left);
        shift(root.right);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }

    @Test
    void Test1() {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7, new TreeNode(6), new TreeNode(9)));
        invertTree(root);
        assertEquals(4, root.val);
        assertEquals(7, root.left.val);
        assertEquals(2, root.right.val);
        assertEquals(9, root.left.left.val);
        assertEquals(6, root.left.right.val);
        assertEquals(3, root.right.left.val);
        assertEquals(1, root.right.right.val);
    }

    @Test
    void Test2() {
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        invertTree(root);
        assertEquals(2, root.val);
        assertEquals(3, root.left.val);
        assertEquals(1, root.right.val);
    }
}
