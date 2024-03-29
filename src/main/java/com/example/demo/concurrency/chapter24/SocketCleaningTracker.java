package com.example.demo.concurrency.chapter24;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.net.Socket;

class SocketCleaningTracker {
    private static final ReferenceQueue<Object> queue = new ReferenceQueue<>();

    static {
        new Cleaner().start();
    }

    static void track(Socket socket){
        new Tracker(socket, queue);
    }

    private static class Cleaner extends Thread{
        private Cleaner(){
            super("SocketCleaningTracker");
            setDaemon(true);
        }

        @Override
        public void run() {
            for (;;){
                try {
                    Tracker tracker = (Tracker)queue.remove();
                    tracker.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Tracker extends PhantomReference<Object>{
        private final Socket socket;

        Tracker(Socket socket, ReferenceQueue<? super Object> queue) {
            super(socket, queue);
            this.socket = socket;
        }

        public void close(){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
