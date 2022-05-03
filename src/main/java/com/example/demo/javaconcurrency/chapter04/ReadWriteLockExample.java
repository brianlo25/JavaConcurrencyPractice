package com.example.demo.javaconcurrency.chapter04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private List<String> dataList = new ArrayList<>();
    public void add(String data) {
        writeLock.lock();
        try {
            dataList.add(data);
        } finally {
            writeLock.unlock();
        }
    }
    public String get(int index) {
        readLock.lock();
        try {
            return dataList.get(index);
        } finally {
            readLock.unlock();
        }
    }
}
