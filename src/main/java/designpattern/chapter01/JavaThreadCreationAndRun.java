package designpattern.chapter01;

public class JavaThreadCreationAndRun {
    static class Helper implements Runnable{
        private final String message;

        Helper(String message) {
            this.message = message;
        }

        private void doSomething(String message){
            System.out.println("The doSomething method was execute by thread:" + Thread.currentThread().getName());
            System.out.println("Do something with " + message);
        }

        @Override
        public void run() {
            doSomething(message);
        }
    }

    public static void main(String[] args) {
        System.out.println("The main method was executed by thread:" + Thread.currentThread().getName());
        Helper helper = new Helper("Java Thread Anywhere");

        Thread thread = new Thread(helper);
        thread.setName("A-Worker-Thread");
        thread.start();
    }
}
