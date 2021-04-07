package concurrency.chapter14;

public class Singleton6 {
    private byte[] data = new byte[1024];

    private Singleton6(){};

    private static class Holder{
        private static Singleton6 instance = new Singleton6();
    }

    public static Singleton6 getInstance(){
        return Holder.instance;
    }
}
