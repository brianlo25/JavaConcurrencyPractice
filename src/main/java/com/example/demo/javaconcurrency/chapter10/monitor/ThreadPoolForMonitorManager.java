package com.example.demo.javaconcurrency.chapter10.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
@Component
public class ThreadPoolForMonitorManager {
    @Autowired
    ThreadPoolConfigureProperties poolConfigureProperties;

    private final ConcurrentMap<String, ThreadPoolExecutorForMonitor> threadPoolExecutorForMonitorConcurrentMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        poolConfigureProperties.getExecutors().forEach(threadPoolProperties -> {
            if (!threadPoolExecutorForMonitorConcurrentMap.containsKey(threadPoolProperties.getPoolName())) {
                ThreadPoolExecutorForMonitor executorForMonitor = new ThreadPoolExecutorForMonitor(
                        threadPoolProperties.getCorePoolSize(),
                        threadPoolProperties.getMaximumPoolSize(),
                        threadPoolProperties.getKeepAliveTime(),
                        threadPoolProperties.getUnit(),
                        new LinkedBlockingQueue<>(threadPoolProperties.getQueueCapacity())
                );
                threadPoolExecutorForMonitorConcurrentMap.put(threadPoolProperties.getPoolName(), executorForMonitor);
            }
        });
    }

    public ThreadPoolExecutorForMonitor getThreadPoolExecutor(String poolName) {
        ThreadPoolExecutorForMonitor threadPoolExecutorForMonitor = threadPoolExecutorForMonitorConcurrentMap.get(poolName);
        if (threadPoolExecutorForMonitor == null) {
            throw new RuntimeException("找不到名字為" + poolName + "的線程池");
        }
        return threadPoolExecutorForMonitor;
    }

    public ConcurrentMap<String, ThreadPoolExecutorForMonitor> getThreadPoolExecutorForMonitorConcurrentMap() {
        return this.threadPoolExecutorForMonitorConcurrentMap;
    }
}
