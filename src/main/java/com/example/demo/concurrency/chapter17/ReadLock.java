package com.example.demo.concurrency.chapter17;

public class ReadLock implements Lock{
    private final ReadWriterLockImpl readWriterLock;

    public ReadLock(ReadWriterLockImpl readWriterLock) {
        this.readWriterLock = readWriterLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriterLock.getMutex()){
            while (readWriterLock.getWritingWriters() > 0 || (readWriterLock.getPreferWriter()) && readWriterLock.getWaitingWriters() > 0){
                readWriterLock.getMutex().wait();
            }

            readWriterLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriterLock.getMutex()){
            readWriterLock.decrementReadingReaders();
            readWriterLock.changePrefer(true);
            readWriterLock.getMutex().notifyAll();
        }
    }
}
