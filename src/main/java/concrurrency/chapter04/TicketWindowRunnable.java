package concrurrency.chapter04;

public class TicketWindowRunnable implements Runnable{

    private static final int MAX = 500;

    private int index = 1;

    private final static Object MUTEX = new Object();

    @Override
    public void run() {
        synchronized (MUTEX){
            while (index <= MAX){
                System.out.println(Thread.currentThread() +"的號碼是:" + (index++));
            }
        }
    }

    public static void main(String[] args) {
        TicketWindowRunnable task = new TicketWindowRunnable();
        Thread windowThread1 = new Thread(task, "一號窗口");
        Thread windowThread2 = new Thread(task, "二號窗口");
        Thread windowThread3 = new Thread(task, "三號窗口");
        Thread windowThread4 = new Thread(task, "四號窗口");

        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
        windowThread4.start();
    }
}
