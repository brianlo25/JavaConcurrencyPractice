package com.example.demo.concurrentcore.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Threads(5)
public class JMHExample06 {

    @State(Scope.Thread)
    public static class Test{

        public Test(){
            System.out.println("create instance");
        }

        public void method(){

        }
    }

    @Benchmark
    public void test(Test test){
        test.method();
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample06.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
}
