package javaconcurrency.chapter05.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ConditionExampleWait conditionExampleWait = new ConditionExampleWait(lock, condition);
        ConditionExampleSignal conditionExampleSignal = new ConditionExampleSignal(lock, condition);

        new Thread(conditionExampleWait).start();
        Thread.sleep(1000);
        new Thread(conditionExampleSignal).start();
    }
}
