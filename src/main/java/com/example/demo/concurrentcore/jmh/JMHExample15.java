package com.example.demo.concurrentcore.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHExample15 {

    private final double x1 = 124.456;
    private final double x2 = 342.456;

    private double y1 = 124.456;
    private double y2 = 342.456;

    @Benchmark
    public double returnDirect(){
        return 42620.703936d;
    }

    @Benchmark
    public double returnCalculate_1(){
        return x1 * x2;
    }

    @Benchmark
    public double returnCalculate_2(){
        return Math.log(y1) * Math.log(y2);
    }

    @Benchmark
    public double returnCalculate_3(){
        return Math.log(x1) * Math.log(x2);
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample15.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
