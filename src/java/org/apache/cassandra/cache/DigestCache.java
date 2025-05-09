package org.apache.cassandra.cache;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

public class DigestCache {
    private static final ConcurrentHashMap<DigestCacheKey, ByteBuffer> cache = new ConcurrentHashMap<>();

    public static ByteBuffer get(DigestCacheKey key) {
        return cache.get(key);
    }

    public static void put(DigestCacheKey key, ByteBuffer digest) {
        cache.put(key, digest.duplicate());
    }

    public static void invalidate(DigestCacheKey key) {
        cache.remove(key);
    }

    public static void clear() {
        cache.clear();
    }
}