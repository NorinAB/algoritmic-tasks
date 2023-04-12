package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://leetcode.com/problems/simplify-path/
//
// Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style
// file system, convert it to the simplified canonical path.
//
// In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the
// directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
// For this problem, any other format of periods such as '...' are treated as file/directory names.
//
// The canonical path should have the following format:
//
// The path starts with a single slash '/'.
// Any two directories are separated by a single slash '/'.
// The path does not end with a trailing '/'.
// The path only contains the directories on the path from the root directory to the target file or directory
// (i.e., no period '.' or double period '..')
// Return the simplified canonical path.
//
// Example 1:
// Input: path = "/home/"
// Output: "/home"
// Explanation: Note that there is no trailing slash after the last directory name.
//
// Example 2:
// Input: path = "/../"
// Output: "/"
// Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
//
// Example 3:
// Input: path = "/home//foo/"
// Output: "/home/foo"
// Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
//
// Constraints:
// 1 <= path.length <= 3000
// path consists of English letters, digits, period '.', slash '/' or '_'.
// path is a valid absolute Unix path.
//
// Complexity:
// 	algorithmic O(n)
// 	memory O(n)
class SImplifyPathTests {

    public String simplifyPath(String path) {
        LinkedList<String> stack = new LinkedList<>();
        for (String part : path.split("/")) {
            if (Objects.equals(part, ".") || Objects.equals(part, "")) {
                continue;
            }
            if (Objects.equals(part, "..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(part);
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append("/").append(stack.pollLast());
        }
        if (result.length() == 0) {
            return "/";
        } else {
            return result.toString();
        }
    }

    @Test
    void testCase1() {
        assertEquals("/foo/bar/baz", simplifyPath("/foo/../test/../test/../foo//bar/./baz"));
    }

    @Test
    void testCase2() {
        assertEquals("/", simplifyPath("/../../../../"));
    }

    @Test
    void testCase3() {
        assertEquals("/home", simplifyPath("/home/"));
    }

    @Test
    void testCase4() {
        assertEquals("/", simplifyPath("/../"));
    }

    @Test
    void testCase5() {
        assertEquals("/home/foo", simplifyPath("/home//foo/"));
    }

}
