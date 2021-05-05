package concurrency.chapter27;

public class ActiveDaemonThread extends Thread{
    private final ActiveMessageQueue queue;

    public ActiveDaemonThread(ActiveMessageQueue queue) {
        super("ActiveDaemonThread");
        this.queue = queue;
        setDaemon(true);
    }

    @Override
    public void run() {
        for (;;){
            MethodMessage methodMessage = this.queue.take();
            methodMessage.execute();
        }
    }
}
