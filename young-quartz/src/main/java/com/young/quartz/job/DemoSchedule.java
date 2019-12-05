package com.young.quartz.job;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author 2460798168@qq.com
 * @date 2019/12/3 11:40
 */
public class DemoSchedule {

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        /**
         * 1、创建调度器Scheduler
         */
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        /**
         * 创建JobDetail实例，并与PrintWordsJob类绑定（Job执行内容）
         */
        JobDetail jobDetail = JobBuilder.newJob(DemoJob.class)
                .withIdentity("job1", "triggerGroup1")
                .build();

        /**
         * 构建JobDetail实例 每隔1s执行一次
         */
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .startNow()//立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        /**
                         * 每隔1s执行一次
                         */
                        .withIntervalInSeconds(1)
                        .repeatForever()).build();//一直执行

        /**
         * 执行
         */
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("-------scheduler start!---------");
        scheduler.start();

        /**
         * 睡眠
         */
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("------------scheduler shutdown!--------------");

    }

}
