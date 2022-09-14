package com.example.demo.javaconcurrency.chapter11;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureCallableExample {
    static class CalculationCallable implements Callable<Integer> {
        private int x;
        private int y;

        public CalculationCallable(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("begin call : " + new Date());
            TimeUnit.SECONDS.sleep(2);
            return x + y;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CalculationCallable calculationCallable = new CalculationCallable(1,2);
        FutureTask<Integer> futureTask = new FutureTask<>(calculationCallable);
        new Thread(futureTask).start();
        System.out.println("begin execute future task : " + new Date());
        Integer rs = futureTask.get();
        System.out.println("result : " + rs);
        System.out.println("end execute future task : " + new Date());
    }
}
