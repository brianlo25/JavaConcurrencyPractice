package javaconcurrency.chapter05.join;

public class JoinTest {
    public static int i = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            i = 30;
        });
        t.start();
        t.join();
        System.out.println("rs:" + i);
    }
}
