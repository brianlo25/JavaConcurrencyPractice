package concurrency.chapter27;

import concurrency.chapter19.Future;
import concurrency.chapter19.FutureService;

import java.util.concurrent.TimeUnit;

public class OrderServiceImpl implements OrderService{
    @Override
    public Future<String> findOrderDetails(long orderId) {
        return FutureService.<Long, String >newService().submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("process the orderId -> " + orderId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "The order details information";
        }, orderId, null);
    }

    @Override
    public void order(String account, long orderId) {
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("process the order for account " + account + ",orderId " + orderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
