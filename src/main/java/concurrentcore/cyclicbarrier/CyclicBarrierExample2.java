package concurrentcore.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample2 {

    private static class Tourist implements Runnable{

        private final int touristId;
        private final CyclicBarrier barrier;

        private Tourist(int touristId, CyclicBarrier barrier) {
            this.touristId = touristId;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.printf("Tourist:%d by bus\n", touristId);
            this.spendSeveralSeconds();
            this.waitAndPrint("Tourist:%d Get on the bus, and wait other people reached. \n");

            System.out.printf("Tourist:%d arrival the destination\n", touristId);
            this.spendSeveralSeconds();
            this.waitAndPrint("Tourist:%d Get off the bus, and wait other people get off. \n");
        }

        private void waitAndPrint(String message){
            System.out.printf(message,touristId);
            try {
                barrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void spendSeveralSeconds(){
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier barrier = new CyclicBarrier(11);

        for (int i = 0; i < 10; i++){
            new Thread(new Tourist(i, barrier)).start();
        }

        barrier.await();

        System.out.println("Tour Guide:all of Tourist get on the bus.");

        barrier.await();

        System.out.println("Tour Guide:all of Tourist get off the bus.");
    }
}
