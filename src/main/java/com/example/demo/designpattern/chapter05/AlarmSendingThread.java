package com.example.demo.designpattern.chapter05;

import com.example.demo.designpattern.chapter04.AlarmAgent;
import com.example.demo.designpattern.chapter04.AlarmInfo;
import com.example.demo.designpattern.chapter04.AlarmType;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AlarmSendingThread extends AbstractTerminatableThread{
    private final AlarmAgent alarmAgent = new AlarmAgent();
    private final BlockingQueue<AlarmInfo> alarmQueue;
    private final ConcurrentMap<String, AtomicInteger> submittedAlarmRegistry;

    public AlarmSendingThread(BlockingQueue<String> alarmQueue, ConcurrentMap<String, AtomicInteger> submittedAlarmRegistry) {
        this.alarmQueue = new ArrayBlockingQueue<AlarmInfo>(100);
        this.submittedAlarmRegistry = new ConcurrentHashMap<String, AtomicInteger>();
        alarmAgent.init();
    }

    @Override
    protected void doRun() throws Exception {
        AlarmInfo alarm;
        alarm = alarmQueue.take();
        terminationToken.reservations.decrementAndGet();

        try {
            alarmAgent.sendAlarm(alarm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (AlarmType.RESUME == alarm.getType()){
            String key = AlarmType.FAULT.getMessage() + ":" + alarm.getId() + "@" + alarm.getExtraInfo();
            submittedAlarmRegistry.remove(key);

            key = AlarmType.RESUME.getMessage() + ":" + alarm.getId() + "@" + alarm.getExtraInfo();
            submittedAlarmRegistry.remove(key);
        }
    }

    public int sendAlarm(final AlarmInfo alarmInfo){
        AlarmType type = alarmInfo.getType();
        Integer id = alarmInfo.getId();
        String extraInfo = alarmInfo.getExtraInfo();

        if (terminationToken.isToShutDown()){
            System.out.println("rejected alarm:" + id + "," + extraInfo);
            return -1;
        }

        int duplicateSubmissionCount = 0;

        try {
            AtomicInteger prevSubmittedCounter;

            prevSubmittedCounter = submittedAlarmRegistry.putIfAbsent(type.getMessage() + ":" + id + "@" + extraInfo, new AtomicInteger(0));
            if (null == prevSubmittedCounter){
                terminationToken.reservations.incrementAndGet();
                alarmQueue.put(alarmInfo);
            } else {
                duplicateSubmissionCount = prevSubmittedCounter.incrementAndGet();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return duplicateSubmissionCount;
    }


}
