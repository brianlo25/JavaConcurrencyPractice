package com.example.demo.concurrentcore.reentrantreadwritelock;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Measurement(iterations = 10, time = 1)
@Warmup(iterations = 10, time = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ReentrantReadWriteLockExample2 {
    @State(Scope.Group)
    public static class Test {
        private int x = 10;

        private final Lock lock = new ReentrantLock();

        private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        private final Lock readLock = readWriteLock.readLock();

        public int baseMethod() {
            return x;
        }

        public int lockMethod() {
            lock.lock();
            try {
                return x;
            } finally {
                lock.unlock();
            }
        }

        public int syncMethod() {
            synchronized (this) {
                return x;
            }
        }

        public int readLockMethod() {
            readLock.lock();
            try {
                return x;
            } finally {
                readLock.unlock();
            }
        }
    }

    @GroupThreads(10)
    @Group("base")
    @Benchmark
    public void base(Test test, Blackhole hole) {
        hole.consume(test.baseMethod());
    }

    @GroupThreads(10)
    @Group("lock")
    @Benchmark
    public void testLockMethod(Test test, Blackhole hole) {
        hole.consume(test.lockMethod());
    }

    @GroupThreads(10)
    @Group("sync")
    @Benchmark
    public void testSyncMethod(Test test, Blackhole hole) {
        hole.consume(test.syncMethod());
    }

    @GroupThreads(10)
    @Group("readLock")
    @Benchmark
    public void testReadLockMethod(Test test, Blackhole hole) {
        hole.consume(test.readLockMethod());
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(ReentrantReadWriteLockExample2.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
