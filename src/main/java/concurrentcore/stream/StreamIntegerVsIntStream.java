package concurrentcore.stream;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Warmup(iterations = 20, time = 1)
@Measurement(iterations = 20, time = 1)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class StreamIntegerVsIntStream {
    private Stream<Integer> integerStream;
    private IntStream intStream;

    @Setup(Level.Invocation)
    public void init() {
        this.integerStream = IntStream.range(0 ,100).boxed();
        this.intStream = IntStream.range(0, 100);
    }

    @Benchmark
    public void streamIntegerReduce(Blackhole blackhole) {
        int result = this.integerStream
                .map((Integer i) -> i * 10)
                .reduce(0, (Integer a, Integer b) -> {
                    return a + b;
                });
        blackhole.consume(result);

    }

    @Benchmark
    public void streamIntegerUnboxThenReduce(Blackhole blackhole) {
        int result = this.integerStream
                .mapToInt(Integer::intValue)
                .map((int i) -> i * 10)
                .reduce(0, (int a, int b) -> {
                    return a + b;
                });
        blackhole.consume(result);

    }

    @Benchmark
    public void intStreamReduce(Blackhole blackhole) {
        int result = this.intStream
                .map((int i) -> i * 10)
                .reduce(0, (int a, int b) -> {
                    return a + b;
                });
        blackhole.consume(result);

    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(StreamIntegerVsIntStream.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
}
