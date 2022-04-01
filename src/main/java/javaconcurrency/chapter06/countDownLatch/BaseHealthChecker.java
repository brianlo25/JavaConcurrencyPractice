package javaconcurrency.chapter06.countDownLatch;

import java.util.concurrent.CountDownLatch;

public abstract class BaseHealthChecker implements Runnable{
    private CountDownLatch latch;
    private String serviceName;
    private boolean serviceUp;

    public BaseHealthChecker(CountDownLatch latch, String serviceName) {
        this.latch = latch;
        this.serviceName = serviceName;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            serviceUp = false;
        } finally {
            if (latch != null) {
                latch.countDown();
            }
        }
    }

    public abstract void verifyService();

    public String getServiceName() {
        return serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }
}
