package concurrentcore.phaser;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class PhaserExample3 {
    private static class MyPhaser extends Phaser{
        private final Runnable runnable;

        public MyPhaser(Runnable runnable) {
            super();
            this.runnable = runnable;
        }

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            this.runnable.run();
            return super.onAdvance(phase, registeredParties);
        }
    }
    public static void main(String[] args) {
        final Phaser phaser = new MyPhaser(() -> System.out.println(new Date() + ": all of sub task completed."));

        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                phaser.register();
                try {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    phaser.arriveAndAwaitAdvance();
                    System.out.println(new Date() + ":" + Thread.currentThread() + " complete th work.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "T-" + i).start();
        }
    }
}
