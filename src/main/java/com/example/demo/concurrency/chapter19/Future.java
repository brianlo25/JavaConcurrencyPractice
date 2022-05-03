package com.example.demo.concurrency.chapter19;

public interface Future<T> {
    T get() throws InterruptedException;

    boolean done();
}
