package concurrency.chapter03.exit;

import java.util.concurrent.TimeUnit;

public class InterruptThreadExit2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("I will start work");
                for (;;){
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }
                }

                System.out.println("I will be existing");
            }
        };

        t.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println("System will be shutdown");
        t.interrupt();
    }
}
