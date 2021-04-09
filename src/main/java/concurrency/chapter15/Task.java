package concurrency.chapter15;

@FunctionalInterface
public interface Task<T> {
    T call();
}
