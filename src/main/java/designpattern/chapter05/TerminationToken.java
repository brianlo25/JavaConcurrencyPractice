package designpattern.chapter05;

import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TerminationToken {
    protected volatile boolean toShutDown = false;
    public final AtomicInteger reservations = new AtomicInteger(0);

    private final Queue<WeakReference<Terminatable>> coordinatedThreads;


    public TerminationToken() {
        this.coordinatedThreads = new ConcurrentLinkedQueue<WeakReference<Terminatable>>();
    }

    public boolean isToShutDown(){
        return toShutDown;
    }

    public void setToShutDown(boolean toShutDown){
        this.toShutDown = toShutDown;
    }

    protected void register(Terminatable thread){
        coordinatedThreads.add(new WeakReference<Terminatable>(thread));
    }

    protected void notifyThreadTermination(Terminatable thread){
        WeakReference<Terminatable> wrThread;
        Terminatable otherThread;
        while (null != (wrThread = coordinatedThreads.poll())){
            otherThread = wrThread.get();
            if (null != otherThread && otherThread != thread){
                otherThread.terminate();
            }
        }
    }
}
