package concurrency.chapter14;

import java.net.Socket;
import java.sql.Connection;

public class Singleton4 {
    private byte[] data = new byte[1024];

    private static Singleton4 instance = null;

    Connection conn;

    Socket socket;

    private Singleton4(){
//        this.conn;
//        this.socket;
    };

    public static Singleton4 getInstance(){
        if (null == instance){
           synchronized (Singleton4.class){
               if (null == instance){
                   instance = new Singleton4();
               }
           }
        }
        return instance;
    }
}
