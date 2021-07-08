package concurrentcore.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHExample22 {

    private byte[] alexBytes;

    private AlexClassLoader classLoader;

    @Setup
    public void init() throws IOException {
        this.alexBytes = Files.readAllBytes(
                Paths.get("C:\\Users\\brianlo\\IdeaProjects\\JavaConcurrencyPractice\\target\\classes\\concurrent\\jmh\\Alex.class")
        );
        this.classLoader = new AlexClassLoader(alexBytes);
    }

    @Benchmark
    public Object testClassLoader() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> alexClass = Class.forName("Alex", true, classLoader);
        return alexClass.newInstance();
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHExample22.class.getSimpleName())
                .addProfiler(GCProfiler.class)
                .jvmArgsAppend("-Xmx128M")
                .build();
        new Runner(options).run();
    }

}
