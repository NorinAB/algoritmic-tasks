package com.norin.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

// Дан массив целых чисел, отсортированный по возрастанию.
// Вернуть массив, содержащий элементы исходного массива в квадрате,
// также отсортированный по возрастанию.
// [1, 4, 10] → [1, 16, 100]
// [-5, -3, 0, 1, 2, 4] → [0, 1, 4, 9, 16, 25]
class Tinkoff4Tests {

    public static Integer[] getSquaredArray(Integer[] arr) {
        List<Integer> nArray = new ArrayList<>();
        List<Integer> pArray = new ArrayList<>();
        for (Integer integer : arr) {
            if (integer < 0) {
                nArray.add(integer * integer);
            } else {
                pArray.add(integer * integer);
            }
        }

        List<Integer> result = new ArrayList<>();
        int pIndex = 0;
        Integer pNumber = pArray.isEmpty() ? null : pArray.get(0);
        int nIndex = nArray.size() - 1;
        Integer nNumber = nArray.isEmpty() ? null : nArray.get(nIndex);

        while (pNumber != null && nNumber != null) {
            if (nNumber > pNumber) {
                result.add(pNumber);
                pIndex++;
                if (pArray.size() > pIndex) {
                    pNumber = pArray.get(pIndex);
                } else {
                    pNumber = null;
                }
            } else {
                result.add(nNumber);
                nIndex--;
                if (nIndex >= 0) {
                    nNumber = nArray.get(nIndex);
                } else {
                    nNumber = null;
                }
            }
        }

        while (pIndex < pArray.size()) {
            result.add(pArray.get(pIndex++));
        }

        while (nIndex >= 0) {
            result.add(nArray.get(nIndex--));
        }

        return result.toArray(new Integer[0]);
    }

    @Test
    void testCase1() {
        Assertions.assertArrayEquals(new Integer[]{1, 16, 100}, getSquaredArray(new Integer[]{1, 4, 10}));
    }

    @Test
    void testCase2() {
        Assertions.assertArrayEquals(new Integer[]{0, 1, 4, 9, 16, 25}, getSquaredArray(new Integer[]{-5, -3, 0, 1, 2, 4}));
    }

    @Test
    void testCase3() {
        Assertions.assertArrayEquals(new Integer[]{1, 4, 9}, getSquaredArray(new Integer[]{-3, -2, -1}));
    }

    @Test
    void testCase4() {
        Assertions.assertArrayEquals(new Integer[]{0}, getSquaredArray(new Integer[]{0}));
    }

    @Test
    void testCase5() {
        Assertions.assertArrayEquals(new Integer[]{0, 1, 1}, getSquaredArray(new Integer[]{-1, 0, 1}));
    }

}
