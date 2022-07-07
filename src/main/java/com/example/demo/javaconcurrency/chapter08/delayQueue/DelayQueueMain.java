package com.example.demo.javaconcurrency.chapter08.delayQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

@Slf4j
public class DelayQueueMain {
    private static DelayQueue delayQueue = new DelayQueue();

    public static void main(String[] args) {
        delayQueue.offer(new OrderDelayedTask("1001", 1000));
        delayQueue.offer(new OrderDelayedTask("1002", 3000));
        delayQueue.offer(new OrderDelayedTask("1003", 2000));
        delayQueue.offer(new OrderDelayedTask("1004", 4000));
        delayQueue.offer(new OrderDelayedTask("1005", 6000));
        delayQueue.offer(new OrderDelayedTask("1006", 5000));

        while (true) {
            try {
                Delayed task = delayQueue.take();
                log.info("{}", task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
