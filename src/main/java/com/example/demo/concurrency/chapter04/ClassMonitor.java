package com.example.demo.concurrency.chapter04;

import java.util.concurrent.TimeUnit;

public class ClassMonitor {

    public static synchronized void method1(){
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void method2(){
        synchronized (ClassMonitor.class){
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(ClassMonitor::method1, "t1").start();
        new Thread(ClassMonitor::method2, "t2").start();
    }
}
