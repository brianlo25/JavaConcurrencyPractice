package designpattern.chapter01;

public class ThreadCreationViaSubclass {
    static class CustomThread extends Thread{
        @Override
        public void run() {
            System.out.println("Running...");
        }
    }

    public static void main(String[] args) {
        Thread thread = new CustomThread();
        thread.start();
    }
}
