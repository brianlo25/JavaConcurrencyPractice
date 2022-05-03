package com.example.demo.concurrency.chapter29;

public class UserOfflineEventChannel extends AsyncChannel{
    @Override
    protected void handle(Event message) {
        UserOfflineEvent event = (UserOfflineEvent) message;
        System.out.println("The user[" + event.getUser().getName() + "] is offline");
    }
}
