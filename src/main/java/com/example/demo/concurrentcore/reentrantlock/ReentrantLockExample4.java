package com.example.demo.concurrentcore.reentrantlock;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Measurement(iterations = 10, time = 1)
@Warmup(iterations = 10, time = 1)
@BenchmarkMode(Mode.AverageTime)
@Threads(1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class ReentrantLockExample4 {
    public static class Test {
        private int x = 10;
        private final Lock lock = new ReentrantLock();

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
    }

    private Test test;

    @Setup(Level.Iteration)
    public void setUp() {
        this.test = new Test();
    }

    @Benchmark
    public void base(Blackhole hole) {
        hole.consume(test.baseMethod());
    }

    @Benchmark
    public void testLockMethod(Blackhole hole) {
        hole.consume(test.lockMethod());
    }

    @Benchmark
    public void testSyncMethod(Blackhole hole) {
        hole.consume(test.syncMethod());
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(ReentrantLockExample4.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
