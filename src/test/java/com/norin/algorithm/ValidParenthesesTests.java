package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// https://leetcode.com/problems/valid-parentheses/
// Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
// An input string is valid if:
// Open brackets must be closed by the same type of brackets.
// Open brackets must be closed in the correct order.
//
// Example 1:
// Input: s = "()"
// Output: true
//
// Example 2:
// Input: s = "()[]{}"
// Output: true
//
// Example 3:
// Input: s = "(]"
// Output: false
//
// Constraints:
// 1 <= s.length <= 104
// s consists of parentheses only '()[]{}'.

// Complexity:
// 	algorithmic O(n)
// 	memory O(n)
class ValidParenthesesTests {

	private boolean isStartBracket(char c){
		return c == '(' || c == '[' || c == '{';
	}

	private boolean isLastStartBracketSuitable(Stack<Character> stack, char c) {
		if (stack.isEmpty()) return false;
		Character lastStoredBracket = stack.pop();
		return (lastStoredBracket == '(' && c == ')')
				|| (lastStoredBracket == '[' && c == ']')
				|| (lastStoredBracket == '{' && c == '}');
	}

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (isStartBracket(c)) {
				stack.push(c);
			} else if (!isLastStartBracketSuitable(stack, c)) {
				return false;
			}
		}
		return stack.isEmpty();
	}

	@Test
	void Test1() {
		assertTrue(isValid("()"));
	}

	@Test
	void Test2() {
		assertTrue(isValid("()"));
	}

	@Test
	void Test3() {
		assertFalse(isValid("(]"));
	}
}
