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
public class JMHExample12 {

    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    @Benchmark
    public void test1(){

    }

    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    @Benchmark
    public void test2(){
        Math.log(Math.PI);
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample12.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
