package com.example.demo.javaconcurrency.chapter09;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

public class ConcurrentHashMapExample2 {
    public static void main(String[] args) {
        ConcurrentMap<Integer,Integer> concurrentMap = new ConcurrentHashMap();
        Stream.of(1,2,8,2,5,6,5,8,3,8).forEach(v -> {
            concurrentMap.merge(v, 2, Integer::sum);
        });
        System.out.println(concurrentMap);
    }
}
