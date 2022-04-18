package javaconcurrency.chapter06.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int parties = 4;
        CyclicBarrier barrier = new CyclicBarrier(parties, () -> {
            System.out.println("所有執行續執行完畢，繼續處理其他任務");
        });
        for (int i = 0; i < parties; i++) {
            new ImportDataTask(barrier).start();
        }
    }

    static class ImportDataTask extends Thread {
        private CyclicBarrier cyclicBarrier;
        public ImportDataTask(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println("執行緒" + Thread.currentThread().getName() + "數據導入完畢，等待其他執行緒");
                cyclicBarrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
