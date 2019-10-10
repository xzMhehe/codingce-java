package com.young.quartz;

import com.young.quartz.job.RamJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Hello world!
 *
 */
public class SimpleQuartzApp {
    public static void main( String[] args ) throws SchedulerException, InterruptedException {
        //创建scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        //定义一个JobDetail
        JobDetail job = newJob(RamJob.class).build();
        //定义一个Trigger
        Trigger trigger = newTrigger()
                //定义name和group
                .withIdentity("trigger1", "group1")
                //加入 scheduler之后立刻执行
                .startNow()
                //定时 ，每个1秒钟执行一次
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
                //job需要传递的内容
                //.usingJobData("name", "sdas")
                //重复执行
                //.repeatForever()).build();
                .build();
        scheduler.scheduleJob(job, trigger);
        //运行一段时间后关闭
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.shutdown();
    }
}
