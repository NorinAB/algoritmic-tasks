package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Необходимо написать функцию, которая получает строку с абсолютным UNIX путем и возвращает укороченную версию, удалив все не нужное
//
// Input: "/foo/../test/../test/../foo//bar/./baz"
// Output: "/foo/bar/baz"
// Пояснения
// .. - возврат на директорию выше
// . - означает текущую директорию, по сути просто мусор
// /- просто мусор
class Tinkoff1Tests {

    public String getPath(String input) {
        String[] split = input.split("/");
        Stack<String> stack = new Stack<>();
        for(String s : split) {
            if ("".equals(s) || ".".equals(s)) {
                continue;
            }
            if ("..".equals(s) && !stack.isEmpty()) {
                stack.pop();
                continue;
            }
            stack.push(s);
        }
        return "/" + String.join("/", stack);
    }

    @Test
    void testCase1() {
        assertEquals("/foo/bar/baz", getPath("/foo/../test/../test/../foo//bar/./baz"));
    }
    @Test
    void testCase2() {
        assertEquals("/", getPath("/../../../../"));
    }

}
