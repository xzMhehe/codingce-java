package com.young.quartz.job;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @Author: Jiangjun
 * @Date: 2019/10/10 11:47
 */
public class HelloQuartz  implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDetail detail = jobExecutionContext.getJobDetail();
        String name = detail.getJobDataMap().getString("name");
        System.out.println("my job name is  " + name + " at " + new Date());

    }
}
