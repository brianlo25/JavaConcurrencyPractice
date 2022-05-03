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
@Measurement(iterations = 10, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHExample16 {

    private int x = 1;
    private int y = 2;

    @Benchmark
    public int measure(){
        return (x + y);
    }

    private int loopCompute(int times){
        int result = 0;
        for (int i = 0; i < times; i++){
            result += (x + y);
        }
        return result;
    }

    @OperationsPerInvocation
    @Benchmark
    public int measureLoop_1(){
        return loopCompute(1);
    }

    @OperationsPerInvocation(10)
    @Benchmark
    public int measureLoop_10(){
        return loopCompute(10);
    }

    @OperationsPerInvocation(100)
    @Benchmark
    public int measureLoop_100(){
        return loopCompute(100);
    }

    @OperationsPerInvocation(1000)
    @Benchmark
    public int measureLoop_1000(){
        return loopCompute(1000);
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample16.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
