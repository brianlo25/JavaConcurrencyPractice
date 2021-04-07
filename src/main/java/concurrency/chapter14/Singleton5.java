package concurrency.chapter14;

import java.net.Socket;
import java.sql.Connection;

public class Singleton5 {
    private byte[] data = new byte[1024];

    private volatile static Singleton5 instance = null;

    Connection conn;

    Socket socket;

    private Singleton5(){
//        this.conn;
//        this.socket;
    };

    public static Singleton5 getInstance(){
        if (null == instance){
            synchronized (Singleton5.class){
                if (null == instance){
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
