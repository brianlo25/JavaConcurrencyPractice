package com.example.demo.concurrency.chapter28;

public interface EventExceptionHandler {
    void handle(Throwable cause, EventContext context);
}
