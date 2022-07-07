package com.example.demo.javaconcurrency.chapter08.delayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class OrderDelayedTask implements Delayed {

    private String orderId;
    private long start = System.currentTimeMillis();
    private long time;

    public OrderDelayedTask(String orderId, long time) {
        this.orderId = orderId;
        this.time = time;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((start + time) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "OrderDelayedTask{" +
                "orderId='" + orderId + '\'' +
                ", start=" + start +
                ", time=" + time +
                '}';
    }
}
