package com.example.demo.javaconcurrency.chapter08.CoR;

import lombok.Data;

@Data
public class Request {
    private String name;

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
