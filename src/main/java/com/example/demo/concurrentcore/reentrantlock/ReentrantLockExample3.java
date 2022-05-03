package com.example.demo.concurrentcore.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample3 {
    private static class Accumulator {
        private static final Lock lock = new ReentrantLock();
        private int x = 0;
        private int y = 0;

        void addX() {
            lock.lock();
            try {
                x++;
            } finally {
                lock.unlock();
            }
        }

        void addY() {
            lock.lock();
            try {
                y++;
            } finally {
                lock.unlock();
            }
        }

        int getX(){
            lock.lock();
            try {
                return x;
            } finally {
                lock.unlock();
            }
        }

        int getY(){
            lock.lock();
            try {
                return y;
            } finally {
                lock.unlock();
            }
        }
    }

    private static class AccumulatorThread extends Thread {
        private final Accumulator accumulator;

        private AccumulatorThread(Accumulator accumulator) {
            this.accumulator = accumulator;
        }

        @Override
        public void run() {
            while (true) {
                accumulator.addX();
                accumulator.addY();
                if (accumulator.getX() != accumulator.getY()){
                    System.out.printf("The x:%d not equals yL%d\n", accumulator.getX(), accumulator.getY());
                }
            }
        }
    }

    public static void main(String[] args) {
        final Accumulator accumulator = new Accumulator();
        for (int i = 0; i < 10; i++) {
            new AccumulatorThread(accumulator).start();
        }
    }
}
