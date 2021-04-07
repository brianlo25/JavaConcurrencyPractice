package concurrency.chapter14;

public class Singleton8 {
    private byte[] data = new byte[1024];

    Singleton8(){}

    private enum EnumHolder{
        INSTANCE;
        private Singleton8 instance;

        EnumHolder(){
            this.instance = new Singleton8();
        }

        private Singleton8 getInstance(){
            return instance;
        }
    }

    public static Singleton8 getInstance(){
        return EnumHolder.INSTANCE.getInstance();
    }
}
