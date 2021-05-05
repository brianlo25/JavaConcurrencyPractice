package concurrency.chapter27;

import concurrency.chapter19.Future;

public interface OrderService {

    Future<String> findOrderDetails(long orderId);

    void order(String account, long orderId);
}
