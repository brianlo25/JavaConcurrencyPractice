package com.example.demo.concurrency.chapter29;

public class Event implements Message{

    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
