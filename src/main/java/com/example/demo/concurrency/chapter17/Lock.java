package com.example.demo.concurrency.chapter17;

public interface Lock {
    void lock() throws InterruptedException;

    void unlock();
}
