package com.example.demo.javaconcurrency.chapter05.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class GpArrayBlockingQueue<E> {
    private ReentrantLock lock = new ReentrantLock();
    //阻塞生產者執行續
    private Condition notFull = lock.newCondition();
    //阻塞消費者執行續
    private Condition notEmpty = lock.newCondition();

    private E[] elements;

    private int count;

    private int headIndex;

    private int tailIndex;

    public GpArrayBlockingQueue(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity cannot be less than zero");
        }
        this.elements = (E[]) new Object[capacity];
    }

    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            while (count == elements.length) {
                notFull.await();
            }
            elements[tailIndex] = e;
            System.out.println("tailIndex :" + tailIndex);
            if (++tailIndex == elements.length) {
                tailIndex = 0;
            }
            ++count;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            E e = elements[headIndex];
            System.out.println("headIndex :" + headIndex);
            if (++headIndex == elements.length) {
                headIndex = 0;
            }
            --count;
            notFull.signalAll();
            return e;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        GpArrayBlockingQueue gpArrayBlockingQueue =  new GpArrayBlockingQueue<Object>(5);
        new Thread(() -> {
            int i = 10;
            while (i >= 0) {
                Object o = new Object();
                try {
                    gpArrayBlockingQueue.put(o);
                    i--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            int i = 10;
            while (i >= 0) {
                try {
                    gpArrayBlockingQueue.take();
                    i--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
