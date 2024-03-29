package com.example.demo.designpattern.chapter04;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class AlarmAgent {
    private volatile boolean connectedToServer = false;

    private final Predicate agentConnected = new Predicate() {
        @Override
        public boolean evaluate() {
            return connectedToServer;
        }
    };

    private final Blocker blocker = new ConditionVarBlocker();

    private final Timer heartbeatTimer = new Timer(true);

    public void sendAlarm(final AlarmInfo alarmInfo) throws Exception{
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {
            @Override
            public Void call() throws Exception {
                doSendAlarm(alarmInfo);
                return null;
            }
        };

        blocker.callWithGuard(guardedAction);
    }

    private void doSendAlarm(AlarmInfo alarmInfo){
        System.out.println("Sending alarm " + alarmInfo.getExtraInfo());
        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        Thread connectingThread = new Thread(new ConnectingTask());
        connectingThread.start();
        heartbeatTimer.schedule(new HeartbeatTask(), 6000, 2000);
    }

    public void disconnect(){
        System.out.println("disconnected from alarm server");
        connectedToServer = false;
    }

    protected void onConnected(){
        try {
            blocker.signalAfter(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    connectedToServer = true;
                    System.out.println("connect to server.");
                    return Boolean.TRUE;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDisconnected(){
        connectedToServer = false;
    }

    private class ConnectingTask implements Runnable{

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            onConnected();
        }
    }

    private class HeartbeatTask extends TimerTask{

        @Override
        public void run() {
            if (!testConnection()){
                onDisconnected();
                reconnect();
            }
        }

        private boolean testConnection(){
            return true;
        }

        private void reconnect(){
            ConnectingTask connectingTask = new ConnectingTask();
            connectingTask.run();
        }
    }
}
