package concurrency.chapter04;

import java.util.concurrent.TimeUnit;

public class ThisMonitor {

    public synchronized void method1(){
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method2(){
        synchronized (this){
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThisMonitor thisMonitor = new ThisMonitor();
        new Thread(thisMonitor::method1, "t1").start();
        new Thread(thisMonitor::method2, "t2").start();
    }
}
