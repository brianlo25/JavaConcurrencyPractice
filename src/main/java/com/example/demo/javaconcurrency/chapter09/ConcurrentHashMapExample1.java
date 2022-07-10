package com.example.demo.javaconcurrency.chapter09;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashMapExample1 {
    private static final ConcurrentMap<String, Long> USER_ACCESS_COUNT = new ConcurrentHashMap<>(64);
    public static void main(String[] args) throws InterruptedException {
        Runnable function = () -> {
            for (int i = 0; i < 10; i++) {
                Long accessCount = USER_ACCESS_COUNT.get("mic");
                if (accessCount == null) {
                    if (USER_ACCESS_COUNT.putIfAbsent("mic", 1L) == null) {
                        System.out.println(Thread.currentThread().getName() + ", value = " + USER_ACCESS_COUNT.get("mic"));
                        continue;
                    }
                } else {
                    if (USER_ACCESS_COUNT.replace("mic", accessCount, accessCount + 1)) {
                        System.out.println(Thread.currentThread().getName() + ", value = " + USER_ACCESS_COUNT.get("mic"));
                        continue;
                    }
                }
            }
        };

        Thread thread1 = new Thread(function, "Thread-a");
        thread1.start();
        Thread thread2 = new Thread(function, "Thread-b");
        thread2.start();
    }
}
