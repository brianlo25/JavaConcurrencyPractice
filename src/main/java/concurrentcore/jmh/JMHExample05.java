package concurrentcore.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Measurement(iterations = 5)
@Warmup(iterations = 2)
public class JMHExample05 {

    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Benchmark
    public void test() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample05.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
