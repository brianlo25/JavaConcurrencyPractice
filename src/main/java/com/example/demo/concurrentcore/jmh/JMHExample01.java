package com.example.demo.concurrentcore.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 10, time = 1)
public class JMHExample01 {
    private final static String DATA = "DUMMY DATA";

    private List<String> arrayList;
    private List<String> linkedList;

    @Setup(Level.Iteration)
    public void setUp(){
        this.arrayList = new ArrayList<>();
        this.linkedList = new LinkedList<>();
    }

    @Benchmark
    public List<String> arrayListAdd(){
        this.arrayList.add(DATA);
        return this.arrayList;
    }

    @Benchmark
    public List<String> linkedListAdd(){
        this.linkedList.add(DATA);
        return this.linkedList;
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample01.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opts).run();
    }
}
