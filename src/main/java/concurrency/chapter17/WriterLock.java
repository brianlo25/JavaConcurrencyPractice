package concurrency.chapter17;

public class WriterLock implements Lock{
    private final ReadWriterLockImpl readWriterLock;

    public WriterLock(ReadWriterLockImpl readWriterLock) {
        this.readWriterLock = readWriterLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriterLock.getMutex()){
            try {
                readWriterLock.incrementWaitingWriters();
                while (readWriterLock.getReadingReaders() > 0 || readWriterLock.getWritingWriters() > 0){
                    readWriterLock.getMutex().wait();
                }
            } finally {
                this.readWriterLock.decrementWaitingWriters();
            }
            readWriterLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriterLock.getMutex()){
            readWriterLock.decrementWritingWriters();
            readWriterLock.changePrefer(false);
            readWriterLock.getMutex().notifyAll();
        }
    }
}
