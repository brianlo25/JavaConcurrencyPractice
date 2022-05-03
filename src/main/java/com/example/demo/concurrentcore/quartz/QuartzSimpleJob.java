package com.example.demo.concurrentcore.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzSimpleJob implements Job {
    private static Logger LOG = LoggerFactory.getLogger(QuartzSimpleJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("Simple execution information");
    }
}
