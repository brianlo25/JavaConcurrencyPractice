package concrurrency.chapter09;

public class Simple {

    static {
        System.out.println("I will be initialized");
    }

    public static int x = 10;

    public static void test(){

    };

    public static void main(String[] args) throws ClassNotFoundException {
//        int y = Simple.x;
//        Simple.test();
        Class.forName("concrurrency.chapter09.Simple");
    }
}
