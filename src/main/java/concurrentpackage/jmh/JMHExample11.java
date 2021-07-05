package concurrentpackage.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Threads(5)
@State(Scope.Thread)
public class JMHExample11 {

    private List<String> list;

    @Setup
    public void setUp(){
        this.list = new ArrayList<>();
    }

    @Benchmark
    public void measureRight(){
        this.list.add("Test");
    }

    @Benchmark
    public void measureWrong(){

    }

    @TearDown
    public void tearDown(){
        assert this.list.size() > 0 : "The list elements must greater than zero";
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample11.class.getSimpleName())
                .jvmArgs("-ea")
                .build();
        new Runner(options).run();
    }
}
