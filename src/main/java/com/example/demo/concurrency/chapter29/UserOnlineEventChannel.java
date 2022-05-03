package com.example.demo.concurrency.chapter29;

public class UserOnlineEventChannel extends AsyncChannel{
    @Override
    protected void handle(Event message) {
        UserOnlineEvent event = (UserOnlineEvent) message;
        System.out.println("The user[" + event.getUser().getName() + "] is online");
    }
}
