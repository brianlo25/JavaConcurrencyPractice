package concurrentcore.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class JMHExample04 {

    @BenchmarkMode(Mode.AverageTime)
    @Benchmark
    public void testAverageTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void testThroughput() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @BenchmarkMode(Mode.SampleTime)
    @Benchmark
    public void testSampleTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @Warmup(iterations = 0)
    @BenchmarkMode(Mode.SingleShotTime)
    @Benchmark
    public void testSingleShotTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Benchmark
    public void testThroughputAndAverageTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    @BenchmarkMode({Mode.All})
    @Benchmark
    public void testAll() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample04.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
