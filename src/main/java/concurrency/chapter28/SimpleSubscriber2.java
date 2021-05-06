package concurrency.chapter28;

public class SimpleSubscriber2 {
    @Subscribe
    public void method1(String message){
        System.out.println("==SimpleSubscribe2==method1==" + message);
    }
    @Subscribe(topic = "test")
    public void method2(String message){
        System.out.println("==SimpleSubscribe2==method2==" + message);
    }
}
