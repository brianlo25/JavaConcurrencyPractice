package javaconcurrency.chapter03;

public class JvmExample {
    private int count;
    public static void main(String[] args) throws InterruptedException {
        JvmExample example = new JvmExample();
        Thread t1 = new Thread(new RunnerTask(example), "t1");
        Thread t2 = new Thread(new RunnerTask(example), "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
    static class RunnerTask implements Runnable {
        private JvmExample jvmExample;

        public RunnerTask(JvmExample jvmExample) {
            this.jvmExample = jvmExample;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                jvmExample.count++;
            }
            System.out.println(Thread.currentThread().getName() + " : " + jvmExample.count);
        }
    }
}
