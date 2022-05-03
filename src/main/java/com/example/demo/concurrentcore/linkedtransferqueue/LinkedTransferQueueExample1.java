package com.example.demo.concurrentcore.linkedtransferqueue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class LinkedTransferQueueExample1 {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        queue.add("hello");
        queue.offer("world");
        queue.put("Java");
        new Thread(() -> {
            try {
                queue.transfer("Alex");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("current thread exit.");
        }).start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(queue.take());
        queue.put("Scala");
        System.out.println(queue.poll());
        System.out.println(queue.take());
        System.out.println(queue.take());
    }
}
