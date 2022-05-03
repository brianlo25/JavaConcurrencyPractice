package com.example.demo.designpattern.chapter05;

public class AlarmMgr {
    private static final AlarmMgr INSTANCE = new AlarmMgr();

    private volatile boolean shutDownRequested = false;

}
