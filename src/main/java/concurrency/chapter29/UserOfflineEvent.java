package concurrency.chapter29;

public class UserOfflineEvent extends UserOnlineEvent{
    public UserOfflineEvent(User user) {
        super(user);
    }
}
