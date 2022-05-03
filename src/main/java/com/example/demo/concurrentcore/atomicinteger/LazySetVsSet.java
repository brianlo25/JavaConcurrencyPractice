package com.example.demo.concurrentcore.atomicinteger;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Measurement(iterations = 10, time = 1)
@Warmup(iterations = 10, time = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class LazySetVsSet {

    private AtomicInteger ai;

    @Setup(Level.Iteration)
    public void setUp(){
        this.ai = new AtomicInteger(0);
    }

    @Benchmark
    public void testSet(){
        this.ai.set(10);
    }

    @Benchmark
    public void testLazySet(){
        this.ai.lazySet(10);
    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(LazySetVsSet.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opts).run();
    }
}
