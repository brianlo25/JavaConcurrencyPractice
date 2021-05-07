package concurrency.chapter29;

public interface Channel<E extends Message> {
    void dispatch(E message);
}
