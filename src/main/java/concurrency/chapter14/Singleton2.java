package concurrency.chapter14;

public class Singleton2 {
    private byte[] data = new byte[1024];

    private static Singleton2 instance = null;

    private Singleton2(){};

    public static Singleton2 getInstance(){
        if (null == instance){
            instance = new Singleton2();
        }
        return instance;
    }
}
