package com.example.demo.concurrency.chapter19;

@FunctionalInterface
public interface CallBack<T> {
    void call(T t);
}
