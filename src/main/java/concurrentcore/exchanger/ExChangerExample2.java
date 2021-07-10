package concurrentcore.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ExChangerExample2 {
    private interface Closeable{
        void close();

        boolean closed();
    }

    private abstract static class CloseableThread extends Thread implements Closeable{
        protected final Exchanger<String> exchanger;

        private volatile boolean closed = false;

        private CloseableThread(Exchanger<String> exchanger, final String name){
            super(name);
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (!closed){
                this.doExchange();
            }
        }

        protected abstract void doExchange();

        @Override
        public void close() {
            System.out.println(Thread.currentThread() + " will be closed.");
            this.closed = true;
            this.interrupt();
        }

        @Override
        public boolean closed() {
            return this.closed || this.isInterrupted();
        }
    }

    private static class StringGenerator extends CloseableThread{
        private char initialChar = 'A';

        private StringGenerator(Exchanger<String> exchanger, String name) {
            super(exchanger, name);
        }

        @Override
        protected void doExchange() {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < 3; i++){
                randomSleep();
                str.append(initialChar++);
            }
            try {
                if (!this.closed()){
                    exchanger.exchange(str.toString());
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread() + " received the close signal.");
            }
        }
    }

    private static class StringConsumer extends CloseableThread{

        private StringConsumer(Exchanger<String> exchanger, String name) {
            super(exchanger, name);
        }

        @Override
        protected void doExchange() {
            try {
                if (!this.closed()){
                    String data = exchanger.exchange(null);
                    System.out.println("received the data:  " + data);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread() + " received the close signal.");
            }
        }
    }

    private static void randomSleep(){
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Exchanger<String> exchanger = new Exchanger<>();

        StringGenerator generator = new StringGenerator(exchanger, "Generator");
        StringConsumer consumer = new StringConsumer(exchanger, "Consumer");

        consumer.start();
        generator.start();

        TimeUnit.MINUTES.sleep(1);
        consumer.close();
        generator.close();
    }
}
