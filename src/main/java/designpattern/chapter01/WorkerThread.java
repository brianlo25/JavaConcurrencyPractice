package designpattern.chapter01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WorkerThread {
    static class Helper{
        private final BlockingQueue<String> workerQueue = new ArrayBlockingQueue<String>(100);
        private final Thread workerThread = new Thread(){
            @Override
            public void run() {
                String task = null;
                while (true){
                    try {
                        task = workerQueue.take();
                    } catch (InterruptedException e) {
                       break;
                    }
                    System.out.println(doProcess(task));
                }
            }
        };

        public void init(){
            workerThread.start();
        }

        protected String doProcess(String task){
            return task + "->processed.";
        }

        public void submit(String task){
            try {
                workerQueue.offer(task);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }

        public static void main(String[] args) {
            Helper helper = new Helper();
            helper.init();
            helper.submit("Something...");
        }
    }
}
