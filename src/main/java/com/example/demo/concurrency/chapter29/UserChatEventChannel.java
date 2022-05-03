package com.example.demo.concurrency.chapter29;

public class UserChatEventChannel extends AsyncChannel{
    @Override
    protected void handle(Event message) {
        UserChatEvent event = (UserChatEvent) message;
        System.out.println("The user[" + event.getUser().getName() + "] say: " + event.getMessage());
    }
}
