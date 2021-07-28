package concurrentcore.linkedlist;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 20, time = 1)
@Measurement(iterations = 20, time = 1)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class BinarySearch {
    private ArrayList<Integer> arrayList;
    private LinkedList<Integer> linkedList;
    private Random random;

    @Setup(Level.Trial)
    public void setUp() {
        this.random = new Random(System.currentTimeMillis());
        this.arrayList = new ArrayList<>();
        this.linkedList = new LinkedList<>();
        for (int i = 0; i < 10000000; i++){
            arrayList.add(i);
            linkedList.add(i);
        }
    }

    @Benchmark
    public void binarySearchFromArrayList(Blackhole blackhole) {
        int randomValue = random.nextInt(10000000);
        int result = Collections.binarySearch(arrayList, randomValue);
        blackhole.consume(result);
    }

    @Benchmark
    public void binarySearchFromLinkedList(Blackhole blackhole) {
        int randomValue = random.nextInt(10000000);
        int result = Collections.binarySearch(linkedList, randomValue);
        blackhole.consume(result);
    }

    public static void main(String[] args) throws RunnerException {
        final Options opt = new OptionsBuilder()
                .include(BinarySearch.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
