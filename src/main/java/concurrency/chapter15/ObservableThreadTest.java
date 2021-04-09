package concurrency.chapter15;

import concurrency.chapter01.TicketWindow;

import java.util.concurrent.TimeUnit;

public class ObservableThreadTest {
    public static void main(String[] args) {
        final TaskLifeCycle<String> lifeCycle = new TaskLifeCycle<String>() {
            @Override
            public void onStart(Thread thread) {

            }

            @Override
            public void onRunning(Thread thread) {

            }

            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("The result is " + result);
            }

            @Override
            public void onError(Thread thread, Exception e) {

            }
        };

        Observable observableThread = new ObservableThread<String>(lifeCycle, () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finished done.");
            return "Hello Observer";
        });
        observableThread.start();
    }
}
