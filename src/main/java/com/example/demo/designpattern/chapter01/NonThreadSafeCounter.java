package com.example.demo.designpattern.chapter01;

public class NonThreadSafeCounter {
    private int counter = 0;

    public void increment(){
        counter++;
    }

    public int get(){
        return counter;
    }
}
