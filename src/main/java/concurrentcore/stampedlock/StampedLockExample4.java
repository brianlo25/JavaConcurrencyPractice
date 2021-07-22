package concurrentcore.stampedlock;

import concurrentcore.reentrantreadwritelock.ReentrantReadWriteLockExample3;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

@Measurement(iterations = 20, time = 1)
@Warmup(iterations = 20, time = 1)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
public class StampedLockExample4 {

    @State(Scope.Group)
    public static class Test {
        private int x = 10;
        private final Lock lock = new ReentrantLock();
        private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private final Lock readLock = readWriteLock.readLock();
        private final Lock writeLock = readWriteLock.writeLock();
        private final StampedLock stampedLock = new StampedLock();

        public void stampedLockInc() {
            long stamped = stampedLock.writeLock();
            try {
                x++;
            } finally {
                stampedLock.unlockWrite(stamped);
            }
        }

        public int stampedLockGet() {
            long stamped = stampedLock.readLock();
            try {
                return x;
            } finally {
                stampedLock.unlockRead(stamped);
            }
        }

        public int stampedOptimisticReadLockGet() {
            long stamped = stampedLock.tryOptimisticRead();
            if (!stampedLock.validate(stamped)) {
                stampedLock.readLock();
                try {
                    return x;
                } finally {
                    stampedLock.unlockRead(stamped);
                }
            }
            return x;
        }

        public void lockInc() {
            lock.lock();
            try {
                x++;
            } finally {
                lock.unlock();
            }
        }

        public int lockGet() {
            lock.lock();
            try {
               return x;
            } finally {
                lock.unlock();
            }
        }

        public void writeLockInc() {
            writeLock.lock();
            try {
                x++;
            } finally {
                writeLock.unlock();
            }
        }

        public int readLockGet() {
            readLock.lock();
            try {
                return x;
            } finally {
                readLock.unlock();
            }
        }
    }

    @GroupThreads(5)
    @Group("lock")
    @Benchmark
    public void lockInc(Test test){
        test.lockInc();
    }

    @GroupThreads(5)
    @Group("lock")
    @Benchmark
    public void lockGet(Test test, Blackhole blackhole){
        blackhole.consume(test.lockGet());
    }

    @GroupThreads(5)
    @Group("rwLock")
    @Benchmark
    public void writeLockInc(Test test){
        test.writeLockInc();
    }

    @GroupThreads(5)
    @Group("rwLock")
    @Benchmark
    public void readLockGet(Test test, Blackhole blackhole){
        blackhole.consume(test.readLockGet());
    }

    @GroupThreads(5)
    @Group("stampedLock")
    @Benchmark
    public void writeStampedLockInc(Test test){
        test.stampedLockInc();
    }

    @GroupThreads(5)
    @Group("stampedLock")
    @Benchmark
    public void readStampedLockGet(Test test, Blackhole blackhole){
        blackhole.consume(test.stampedLockGet());
    }

    @GroupThreads(5)
    @Group("stampedLockOptimistic")
    @Benchmark
    public void writeStampedLockInc2(Test test){
        test.stampedLockInc();
    }

    @GroupThreads(5)
    @Group("stampedLockOptimistic")
    @Benchmark
    public void readStampedLockGet2(Test test, Blackhole blackhole){
        blackhole.consume(test.stampedOptimisticReadLockGet());
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(StampedLockExample4.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
