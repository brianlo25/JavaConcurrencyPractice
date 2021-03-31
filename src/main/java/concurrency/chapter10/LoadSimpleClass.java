package concurrency.chapter10;

public class LoadSimpleClass {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader("C:\\classLoader2");
        Class<?> aClass = classLoader.loadClass("concurrency.chapter10.SimpleClass");
        System.out.println(classLoader.getParent());
        aClass.newInstance();
    }
}
