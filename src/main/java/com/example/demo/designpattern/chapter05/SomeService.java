package com.example.demo.designpattern.chapter05;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class SomeService {
    private final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
    private final Producer producer = new Producer();
    private final Consumer consumer = new Consumer();

    private class Producer extends AbstractTerminatableThread{
        private int i = 0;

        @Override
        protected void doRun() throws Exception {
            queue.put(String.valueOf(i++));
            consumer.terminationToken.reservations.incrementAndGet();
        }
    }

    private class Consumer extends AbstractTerminatableThread{

        @Override
        protected void doRun() throws Exception {
            String product = queue.take();
            System.out.println("Processing product:" + product);
            try {
                TimeUnit.SECONDS.sleep(RandomUtils.nextInt(5,10));
            } finally {
                terminationToken.reservations.decrementAndGet();
            }
        }
    }

    public void shutDown(){
        producer.terminate(true);
        consumer.terminate();
    }

    public void init(){
        producer.start();
        consumer.start();
    }

    public static void main(String[] args) throws InterruptedException {
        SomeService ss = new SomeService();
        ss.init();
        Thread.sleep(500);
        ss.shutDown();
    }
}
