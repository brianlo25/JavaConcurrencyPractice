package javaconcurrency.chapter07.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {
    private static final Integer MAX = 400;

    static class CalculationTask extends RecursiveTask<Integer> {
        private Integer startValue;
        private Integer endValue;

        public CalculationTask(Integer startValue, Integer endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        protected Integer compute() {
            if (endValue - startValue < MAX) {
                System.out.println("開始計算的部分 : startValue = " + startValue + "; endValue = " + endValue);
                Integer totalValue = 0;
                for (int index = this.startValue; index <= this.endValue; index++) {
                    totalValue += index;
                }
                return totalValue;
            }
            return creatSubTask();
        }

        private Integer creatSubTask() {
            CalculationTask subTask1 = new CalculationTask(startValue, (startValue + endValue)/2);
            subTask1.fork();
            CalculationTask subTask2 = new CalculationTask((startValue + endValue)/2 + 1, endValue);
            subTask2.fork();
            return subTask1.join() + subTask2.join();
        }

        public static void main(String[] args) {
            ForkJoinPool pool = new ForkJoinPool();
            ForkJoinTask<Integer> taskFuture = pool.submit(new CalculationTask(1, 2002));
            try {
                Integer result = taskFuture.get();
                System.out.println("result :" + result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
