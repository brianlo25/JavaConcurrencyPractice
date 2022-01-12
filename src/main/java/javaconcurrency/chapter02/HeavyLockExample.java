package javaconcurrency.chapter02;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

public class HeavyLockExample {
    public static void main(String[] args) throws InterruptedException {
        HeavyLockExample heavy = new HeavyLockExample();
        System.out.println("加鎖之前");
        System.out.println(ClassLayout.parseInstance(heavy).toPrintable());

        Thread t1 = new Thread(() -> {
            synchronized (heavy) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("t1線程搶占了鎖");
        System.out.println(ClassLayout.parseInstance(heavy).toPrintable());
        synchronized (heavy) {
            System.out.println("main線程來搶占鎖");
            System.out.println(ClassLayout.parseInstance(heavy).toPrintable());
        }
    }
}
