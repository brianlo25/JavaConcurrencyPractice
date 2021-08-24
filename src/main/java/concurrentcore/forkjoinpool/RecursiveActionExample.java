package concurrentcore.forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

public class RecursiveActionExample extends RecursiveAction {
    private List<Integer> numbers;
    private static final int THRESHOLD = 10;
    private int start;
    private int end;
    private int factor;

    public RecursiveActionExample(List<Integer> numbers, int start, int end, int factor) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.factor = factor;
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            computeDirectly();
        } else {
            int middle = (end + start) / 2;
            RecursiveActionExample taskOne = new RecursiveActionExample(numbers, start, middle, factor);
            RecursiveActionExample taskTwo = new RecursiveActionExample(numbers, middle, end, factor);
            this.invokeAll(taskOne, taskTwo);
        }
    }

    private void computeDirectly() {
        for (int i = start; i < end; i++) {
            numbers.set(i, numbers.get(i) * factor);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            list.add(ThreadLocalRandom.current().nextInt(1000));
        }
        System.out.println(list);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        RecursiveActionExample forkJoinTask = new RecursiveActionExample(list, 0, 10, 10);
        forkJoinPool.invoke(forkJoinTask);
        System.out.println(list);
    }
}
