package com.young.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/11 9:36
 */
public class TimeRemindJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeRemindJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
