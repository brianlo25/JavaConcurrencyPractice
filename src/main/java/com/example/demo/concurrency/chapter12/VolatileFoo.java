package com.example.demo.concurrency.chapter12;

import java.util.concurrent.TimeUnit;

public class VolatileFoo {
    final static int MAX = 5;

    static volatile int int_value = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = int_value;
            while (localValue < MAX){
                if (int_value != localValue){
                    System.out.printf("The init_value is updated to [%d]\n", int_value);
                    localValue = int_value;
                }
            }
        }, "Reader").start();

        new Thread(() -> {
            int localValue = int_value;
            while (localValue < MAX){
                System.out.printf("The init_value will be changed to [%d]\n", ++localValue);
                int_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}
