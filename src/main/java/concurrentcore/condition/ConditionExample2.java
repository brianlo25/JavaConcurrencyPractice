package concurrentcore.condition;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConditionExample2 {
    private static final Lock lock = new ReentrantLock();

    private static final Condition FULL_CONDITION = lock.newCondition();

    private static final Condition EMPTY_CONDITION = lock.newCondition();

    private static final LinkedList<Long> list = new LinkedList<>();

    private static final int CAPACITY = 100;

    private static long i = 0;

    private static void produce() {
        lock.lock();
        try {
            while (list.size() > CAPACITY) {
                FULL_CONDITION.await();
            }
            i++;
            list.addLast(i);
            System.out.println(Thread.currentThread().getName() + "->" + i);
            FULL_CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void consume() {
        lock.lock();
        try {
            while (list.isEmpty()) {
                EMPTY_CONDITION.await();
            }
            Long value = list.removeFirst();
            System.out.println(Thread.currentThread().getName() + "->" + value);
            EMPTY_CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i ->
                new Thread(() -> {
                    for (;;){
                        produce();
                        sleep();
                    }
                }, "Producer-" + i).start()
        );

        IntStream.range(0, 5).forEach(i ->
                new Thread(() -> {
                    for (;;){
                        consume();
                        sleep();
                    }
                }, "Consumer-" + i).start()
        );
    }
}
