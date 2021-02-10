package concrurrency.chapter06;

import java.util.concurrent.TimeUnit;

public class ThreadGroupPriority {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("group1");
        Thread thread = new Thread(group, () -> {
           while (true){
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }, "thread");
        thread.setDaemon(true);
        thread.start();

        System.out.println("getMaxPriority=" + group.getMaxPriority());
        System.out.println("getPriority=" +thread.getPriority());
        group.setMaxPriority(3);
        System.out.println("getMaxPriority=" + group.getMaxPriority());
        System.out.println("getPriority=" +thread.getPriority());

    }
}
