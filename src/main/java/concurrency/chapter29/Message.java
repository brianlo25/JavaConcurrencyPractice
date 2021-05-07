package concurrency.chapter29;

public interface Message {
    Class<? extends Message> getType();
}
