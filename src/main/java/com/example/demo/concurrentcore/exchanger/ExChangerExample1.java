package com.example.demo.concurrentcore.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ExChangerExample1 {

    private static void randomSleep(){
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            System.out.println(Thread.currentThread() + " start.");
            try {
                randomSleep();
                String data = exchanger.exchange("I am from T1");
                System.out.println(Thread.currentThread() + " received: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " end.");
        }, "T1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread() + " start.");
            try {
                randomSleep();
                String data = exchanger.exchange("I am from T2");
                System.out.println(Thread.currentThread() + " received: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " end.");
        }, "T2").start();
    }
}
