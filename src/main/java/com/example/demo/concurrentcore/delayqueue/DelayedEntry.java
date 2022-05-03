package com.example.demo.concurrentcore.delayqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedEntry implements Delayed {
    private final String value;

    private final long time;

    public DelayedEntry(String value, long delayTime) {
        this.value = value;
        this.time = delayTime + System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long delta = time - System.currentTimeMillis();
        return unit.convert(delta, TimeUnit.MILLISECONDS);
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.time < ((DelayedEntry)o).time) {
            return -1;
        } else if (this.time > ((DelayedEntry)o).time) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "DelayedEntry{" +
                "value='" + value + '\'' +
                ", time=" + time +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedEntry> delayQueue = new DelayQueue<>();
        delayQueue.put(new DelayedEntry("A", 10*1000L));
        delayQueue.put(new DelayedEntry("B", 5*1000L));

        final long timestamp = System.currentTimeMillis();

        System.out.println(delayQueue.poll() == null);

        DelayedEntry value = delayQueue.take();
        System.out.println(value.getValue().equals("B"));
        System.out.println(System.currentTimeMillis() - timestamp >= 5000L);

        value = delayQueue.take();
        System.out.println(value.getValue().equals("A"));
        System.out.println(System.currentTimeMillis() - timestamp >= 10000L);
    }
}
