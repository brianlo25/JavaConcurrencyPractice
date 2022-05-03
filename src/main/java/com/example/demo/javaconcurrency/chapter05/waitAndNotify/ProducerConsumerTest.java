package com.example.demo.javaconcurrency.chapter05.waitAndNotify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerTest {
    public static void main(String[] args) throws InterruptedException {
        Queue<String> bags = new LinkedList<String>();
        int maxSize = 2;
        Producer producer = new Producer(bags, maxSize);
        Consumer consumer = new Consumer(bags,maxSize);
        new Thread(producer).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(consumer).start();
    }
}
