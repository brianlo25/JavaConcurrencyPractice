package com.example.demo.concurrency.chapter24;

public class Request {
    private final String business;

    public Request(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return business;
    }
}
