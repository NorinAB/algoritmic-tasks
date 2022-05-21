package com.norin.algorithm;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

// https://leetcode.com/problems/encode-and-decode-tinyurl/
// TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.
//
// There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
//
// Implement the Solution class:
//
// Solution() Initializes the object of the system.
// String encode(String longUrl) Returns a tiny URL for the given longUrl.
// String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.

class TinyURLTests {

    public class Codec {
        private static final String BASE_URL = "http://tinyurl.com";

        Map<String, String> storage = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            String tinyUrl = getTinyUrl(longUrl);
            while (storage.containsKey(tinyUrl)) {
                tinyUrl = getTinyUrl(longUrl);
            }
            storage.put(tinyUrl, longUrl);
            return tinyUrl;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return storage.get(shortUrl);
        }

        private String getHash(String url) {
            return UUID.randomUUID().toString().substring(0, 5);
        }

        private String getTinyUrl(String longUrl) {
            return BASE_URL + "/" + getHash(longUrl);
        }
    }

    @Test
    void shouldDecodeRight() {
        Codec codec = new Codec();
        assertEquals("https://leetcode.com/problems/design-tinyurl",
                codec.decode(codec.encode("https://leetcode.com/problems/design-tinyurl")));
    }

    @Test
    void shouldStoreAllUrls() {
        Codec codec = new Codec();
        String encoded1 = codec.encode("https://leetcode.com/problems/design-tinyurl1");
        String encoded2 = codec.encode("https://leetcode.com/problems/design-tinyurl2");
        String decoded1 = codec.decode(encoded1);
        String decoded2 = codec.decode(encoded2);

        assertEquals("https://leetcode.com/problems/design-tinyurl1", decoded1);
        assertEquals("https://leetcode.com/problems/design-tinyurl2", decoded2);
    }

    @Test
    void allTinyUrlsShouldBeDifferent() {
        Codec codec = new Codec();
        String encode1 = codec.encode("https://leetcode.com/problems/design-tinyurl1");
        String encode2 = codec.encode("https://leetcode.com/problems/design-tinyurl2");
        assertNotEquals(encode1, encode2);
    }
}
