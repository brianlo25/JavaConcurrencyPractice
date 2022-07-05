package com.example.demo.javaconcurrency.chapter08;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class ProducerAndConsumerProgram {

    static Logger logger = LoggerFactory.getLogger(ProducerAndConsumerProgram.class);

    static class Producer implements Runnable {
        BlockingQueue<String> blockingQueue;

        public Producer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    blockingQueue.put("element" + i);
                    logger.info("{}，生產者生產數據，目前總共的元素個數:{}", Thread.currentThread().getName(), blockingQueue.size());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Consumer implements Runnable {
        BlockingQueue<String> blockingQueue;

        public Consumer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    String element = blockingQueue.take();
                    logger.info("{}，消費者消費數據，目前還剩下的元素個數:{}", Thread.currentThread().getName(), blockingQueue.size());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        new Thread(producer).start();
        Thread.sleep(10);
        new Thread(consumer).start();
    }
}
