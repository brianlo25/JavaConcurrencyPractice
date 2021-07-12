package concurrentcore.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SemaphoreExample1 {

    private static void simulateWork(){
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class LoginService{
        private final Semaphore semaphore;

        public LoginService(int maxPermitLoginAccount){
            this.semaphore = new Semaphore(maxPermitLoginAccount, true);
        }

        public boolean login(){
            boolean login = semaphore.tryAcquire();
            if (login){
                System.out.println(Thread.currentThread() + "login success.");
            }
            return login;
        }

//        public boolean login(){
//            try {
//                semaphore.acquire();
//                System.out.println(Thread.currentThread() + "login success.");
//            } catch (InterruptedException e){
//                return false;
//            }
//
//            return true;
//        }

        public void logOut(){
            semaphore.release();
            System.out.println(Thread.currentThread() + "logout success.");
        }
    }

    public static void main(String[] args) {
        final int MAX_PERMIT_ACCOUNT = 10;

        final LoginService loginService = new LoginService(MAX_PERMIT_ACCOUNT);

        IntStream.range(0, 20).forEach(i ->
                new Thread(() -> {
                    boolean login = loginService.login();

                    if (!login){
                        System.out.println(Thread.currentThread() + "is refused due to max online account.");
                        return;
                    }

                    try {
                        simulateWork();
                    } finally {
                        loginService.logOut();
                    }
                }, "User-" + i).start()
        );
    }
}
