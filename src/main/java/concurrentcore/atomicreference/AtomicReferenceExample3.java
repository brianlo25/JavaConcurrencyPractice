package concurrentcore.atomicreference;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample3 {
    private static AtomicReference<DebitCard> debitCardRef = new AtomicReference<>(new DebitCard("Alex", 0));

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            new Thread("T-" + i){
                @Override
                public void run() {
                   while (true){
                       final DebitCard dc = debitCardRef.get();
                       DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                       if (debitCardRef.compareAndSet(dc, newDC)){
                           System.out.println(newDC);
                       }

                       try {
                           TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                }
            }.start();
        }
    }
}
