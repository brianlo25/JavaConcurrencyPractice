package com.example.demo.concurrency.chapter22;

public class BalkingTest {
    public static void main(String[] args) {
        new DocumentEditThread("C:\\Users\\brianlo\\Desktop", "balking.txt").start();
    }
}
