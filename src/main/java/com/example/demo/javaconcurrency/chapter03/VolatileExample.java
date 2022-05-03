package com.example.demo.javaconcurrency.chapter03;

public class VolatileExample {
    public static volatile boolean stop = false;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
        });
        t1.start();
        System.out.println("begin start thread");
        Thread.sleep(1000);
        stop = true;
    }
}
