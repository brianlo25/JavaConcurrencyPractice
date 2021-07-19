package concurrentcore.reentrantreadwritelock;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample1 {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    private final LinkedList<String> list = new LinkedList<>();

    public void add(String element) {
        writeLock.lock();
        try {
            list.addLast(element);
        } finally {
            writeLock.unlock();
        }
    }

    public String take() {
        writeLock.lock();
        try {
            return list.removeFirst();
        } finally {
            writeLock.unlock();
        }
    }

    public String get(int index) {
        readLock.lock();
        try {
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }
}
