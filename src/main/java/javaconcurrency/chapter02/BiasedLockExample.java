package javaconcurrency.chapter02;

import org.openjdk.jol.info.ClassLayout;

public class BiasedLockExample {
    public static void main(String[] args) throws InterruptedException {
        BiasedLockExample example = new BiasedLockExample();
        System.out.println("加鎖之前");
        System.out.println(ClassLayout.parseInstance(example).toPrintable());
        synchronized (example) {
            System.out.println("加鎖之後");
            System.out.println(ClassLayout.parseInstance(example).toPrintable());
        }
    }
}
