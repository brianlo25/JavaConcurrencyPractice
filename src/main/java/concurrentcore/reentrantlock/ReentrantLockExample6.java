package concurrentcore.reentrantlock;

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
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ReentrantLockExample6 {
    @State(Scope.Group)
    public static class Test {
        private int x = 10;
        private final Lock lock = new ReentrantLock();

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

        public void syncInc() {
            synchronized (this) {
                x++;
            }
        }

        public int syncGet() {
            synchronized (this) {
                return x;
            }
        }
    }

    @GroupThreads(5)
    @Group("lock")
    @Benchmark
    public void lockInc(Test test) {
        test.lockInc();
    }

    @GroupThreads(5)
    @Group("lock")
    @Benchmark
    public void lockGet(Test test, Blackhole blackhole) {
        blackhole.consume(test.lockGet());
    }

    @GroupThreads(5)
    @Group("sync")
    @Benchmark
    public void syncInc(Test test) {
        test.syncInc();
    }

    @GroupThreads(5)
    @Group("sync")
    @Benchmark
    public void syncGet(Test test, Blackhole blackhole) {
        blackhole.consume(test.syncGet());
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(ReentrantLockExample6.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
