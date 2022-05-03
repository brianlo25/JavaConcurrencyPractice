package com.example.demo.concurrency.chapter19;

@FunctionalInterface
public interface Task<IN, OUT> {
    OUT get(IN input);
}
