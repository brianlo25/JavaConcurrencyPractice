package com.example.demo.javaconcurrency.chapter08.CoR;

public class ChainTestMain {
    public static void main(String[] args) {
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();
        PrintProcessor printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
        ValidProcessor validProcessor = new ValidProcessor(printProcessor);
        validProcessor.start();
        Request request = new Request();
        request.setName("Mic");
        validProcessor.processRequest(request);
    }
}
