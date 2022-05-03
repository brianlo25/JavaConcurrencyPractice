package com.example.demo.concurrency.chapter01;

public class TicketWindow extends Thread{
    private final String name;

    private static final int MAX = 50;

    private static int index = 1;


    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println("櫃台:" + name + "當前的號碼是:" + (index++));
        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一號出號機");
        ticketWindow1.start();
        TicketWindow ticketWindow2 = new TicketWindow("二號出號機");
        ticketWindow2.start();
        TicketWindow ticketWindow3 = new TicketWindow("三號出號機");
        ticketWindow3.start();
        TicketWindow ticketWindow4 = new TicketWindow("四號出號機");
        ticketWindow4.start();

    }
}
