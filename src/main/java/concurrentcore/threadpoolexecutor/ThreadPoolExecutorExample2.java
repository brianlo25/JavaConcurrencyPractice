package concurrentcore.threadpoolexecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorExample2 {
    private static class MyThreadFactory implements ThreadFactory {
        private final static String PREFIX = "Alex";
        private final static AtomicInteger INC = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            ThreadGroup group = new ThreadGroup("MyPool");
            Thread thread = new Thread(group, r, PREFIX + "-" + INC.getAndIncrement());
            thread.setPriority(10);
            return thread;
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new MyThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 5; i++){
            executor.execute(() -> System.out.println("Task done by " + Thread.currentThread()));
        }
    }
}
