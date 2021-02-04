package concrurrency.chapter03.priority;

public class ThreadPriority2 {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("test");
        group.setMaxPriority(7);
        Thread thread = new Thread(group, "test-thread");
        thread.setPriority(10);
        System.out.println(thread.getPriority());
    }
}
