package com.young.quartz.job;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/10 11:47
 */
public class HelloQuartz  implements Job {

    private static final Logger LOGGER =LoggerFactory.getLogger(TimeRemindJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDetail detail = jobExecutionContext.getJobDetail();
        String name = detail.getJobDataMap().getString("name");
        System.out.println("my job name is  " + name + " at " + new Date());
        LOGGER.info("Creating a successful");
    }
}
