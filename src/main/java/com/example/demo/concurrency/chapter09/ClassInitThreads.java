package com.example.demo.concurrency.chapter09;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ClassInitThreads {
    static {
        try {
            System.out.println("The ClassInit static code will br invoke.");
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntStream.range(0, 5).forEach(i -> new Thread(ClassInitThreads::new));
    }
}
