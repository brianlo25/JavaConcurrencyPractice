package concurrency.chapter27;

public class ActiveObjectTest {
    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = OrderServiceFactory.toActiveObject(new OrderServiceImpl());
        orderService.order("hello", 453453);
        orderService.findOrderDetails(453453);
        System.out.println("Return immediately");
        Thread.currentThread().join();
    }
}
