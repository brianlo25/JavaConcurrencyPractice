package com.example.demo.javaconcurrency.chapter02;

public class SynchronizedExample {
    public void m1() {
        synchronized (SynchronizedExample.class) {
            while (true) {
                System.out.println("當前訪問的線程 : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedExample se1 = new SynchronizedExample();
        SynchronizedExample se2 = new SynchronizedExample();
        new Thread(() -> se1.m1(), "t1").start();
        new Thread(() -> se2.m1(), "t2").start();
    }
}
