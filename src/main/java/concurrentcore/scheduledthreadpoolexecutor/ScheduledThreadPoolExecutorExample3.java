package concurrentcore.scheduledthreadpoolexecutor;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorExample3 {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);

        ScheduledFuture<?> future = scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            System.out.println(new Date());
        }, 10, 10, TimeUnit.SECONDS);
    }
}
