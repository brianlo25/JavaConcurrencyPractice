package com.example.demo.concurrency.chapter25;

@FunctionalInterface
public interface CacheLoader<K, V> {
    V load(K k);
}
