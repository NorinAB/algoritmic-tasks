package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// https://leetcode.com/problems/peeking-iterator/
// Design an iterator that supports the peek operation on an existing iterator in addition to the hasNext and the next operations.
//
// Implement the PeekingIterator class:
//
// PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator iterator.
// int next() Returns the next element in the array and moves the pointer to the next element.
// boolean hasNext() Returns true if there are still elements in the array.
// int peek() Returns the next element in the array without moving the pointer.
// Note: Each language may have a different implementation of the constructor and Iterator, but they all support the int next() and boolean hasNext() functions.

class PeekingIteratorTests {

    class PeekingIterator implements Iterator<Integer> {

        Iterator<Integer> iterator;
        Integer peekedValue;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (peekedValue != null) return peekedValue;
            peekedValue = iterator.next();
            return peekedValue;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (peekedValue != null) {
                Integer tmpValue = peekedValue;
                peekedValue = null;
                return tmpValue;
            }
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return peekedValue != null || iterator.hasNext();
        }
    }

    @Test
    void testCase1() {
        PeekingIterator peekingIterator = new PeekingIterator(List.of(1, 2, 3).iterator()); // [1,2,3]
        assertEquals(1, peekingIterator.next());    // return 1, the pointer moves to the next element [1,2,3].
        assertEquals(2, peekingIterator.peek());    // return 2, the pointer does not move [1,2,3].
        assertEquals(2, peekingIterator.next());    // return 2, the pointer moves to the next element [1,2,3]
        assertEquals(3, peekingIterator.next());    // return 3, the pointer moves to the next element [1,2,3]
        assertFalse(peekingIterator.hasNext()); // return F
    }

    @Test
    void testCase2() {
        PeekingIterator peekingIterator = new PeekingIterator(List.of(1, 2, 3, 4).iterator()); // [1,2,3]
        assertTrue(peekingIterator.hasNext());
        assertEquals(1, peekingIterator.peek());
        assertEquals(1, peekingIterator.peek());
        assertEquals(1, peekingIterator.next());
        assertEquals(2, peekingIterator.next());
        assertEquals(3, peekingIterator.peek());
        assertEquals(3, peekingIterator.peek());
        assertEquals(3, peekingIterator.next());
        assertEquals(4, peekingIterator.peek());
        assertTrue(peekingIterator.hasNext());
        assertEquals(4, peekingIterator.next());
        assertFalse(peekingIterator.hasNext());
    }
}
