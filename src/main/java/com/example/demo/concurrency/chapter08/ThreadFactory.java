package com.example.demo.concurrency.chapter08;

@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
