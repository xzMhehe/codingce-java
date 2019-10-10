package com.young.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;


public class RamJob implements Job {

    static final Logger logger = Logger.getLogger(RamJob.class.getName());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Say Hello World " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss")));
    }
}
    /**
     * Quartz是一个功能丰富的开源的任务调用系统
     * Quartz 三要素：
     * Scheduler：任务调度器，所有的任务都是从这里开始。
     * Trigger：触发器，定义任务执行的方式、间隔。
     * JobDetail & Job ： 定义任务具体执行的逻辑。
     * Scheduler
     * scheduler 是quartz的核心所在，所有的任务都是通过scheduler开始。
     * scheduler是一个接口类，所有的具体实现类都是通过SchedulerFactory工厂类实现，但是SchedulerFactory有两个具体的实现类
     * DirctSchedulerFactory:默认值加载是当前工作目录下的”quartz.properties”属性文件。如果加载失败，
     * 会去加载org/quartz包下的”quartz.properties”属性文件。一般使用这个实现类就能满足我们的要求。
     * StdSchedulerFactory:这个我也没用过QAQ，听说是为那些想绝对控制 Scheduler 实例是如何生产出的人所
     * 设计的。
     * Trigger
     * 惊奇的发现trigger采用的也是buidler模式.(https://blog.csdn.net/bicheng4769/article/details/80988996)
     * withIdentity() 给触发器一些属性 比如名字，组名。
     * startNow() 立刻启动
     * withSchedule(ScheduleBuilder schedBuilder) 以某种触发器触发。
     * usingJobData(String dataKey, Boolean value) 给具体job传递参数。
     *
     * Trigger的重点内容就是在withSchedule这个方法，从参数开始：查看SchedulerBuilder，这个是个抽象类，一共有4种具体实现方法:
     * ScheduleBuilde:
     * SimpleScheduleBuilde、DailyTimeIntervalScheduleBuilder、CronScheduleBuilder、CalendarIntervalScheduleBuider
     *
     * SimpleScheduleBuilder
     * 最简单的触发器，表示从某一时刻开始，以一定的时间间隔执行任务。
     * 属性：
     *      repeatInterval 重复间隔。
     *      repeatCount 重复次数。
     *
     * DailyTimeIntervalScheduleBuilder
     * 每一天的某一个时间段内，以一定的时间间隔执行任务，可以指定具体的某一天（星期一、星期二、星期三。。）
     * 属性：
     *      intervalUnit 重复间隔（秒、分钟、小时。。。）。
     *      daysOfWeek 具体的星期。 默认 周一到周日
     *      startTimeOfDay 每天开始时间 默认 0.0
     *      endTimeOfDay 每天结束时间，默认 23.59.59
     *      repeatCount 重复次数。 默认是-1 不限次数
     *      interval 每次执行间隔
     *
     * CalendarIntervalScheduleBuilder
     * 和SimpleScheduleBuilder类似，都是表示从某一时刻开始，以一定时间间隔执行任务。但是SimpleScheduleBuilder无法指定一些特殊情况，比如每个月执行一次，每周执行一次、每一年执行一次
     * 属性：
     *      interval 执行间隔
     *      intervalUnit 执行间隔的单位（秒，分钟，小时，天，月，年，星期）
     *
     * 接下来是最刁的也是最常用的最自由的CronScheduleBuilder
     *
     * CronScheduleBuilder
     * 以上几个例子都可以使用cron表达式来表示。
     * 属性：
     * cron表达式。
     * 这没什么 最主要解释写cron表达式，可以自行google。
     *
     *
     * JobDetail & Job
     * jobdetail 就是对job的定义，而job是具体执行的逻辑内容。
     * 具体的执行的逻辑需要实现 job类，并实现execute方法。
     * 这里为什么需要有个JobDetai来作为job的定义，为什么不直接使用job？
     * 解释：如果使用jobdetail来定义，那么每次调度都会创建一个new job实例，这样带来的好处就是任务并发执行的时候，互不干扰，不会对临界资源造成影响。
     *
     * 项目中出现的问题：
     * cron表达式怎么写？
     * 答：这个问题，有点尴尬，本来quartz框架用起来就很简单，重点都是在于cron表达式如何写。所以大家还是多google，这里给个地址，我当时看的一篇专栏。
     * https://zhuanlan.zhihu.com/p/35629505
     * 如何禁止并发执行？
     * 答：项目中出现了一种情况，本来job执行时间只需要10s，但是由于数据库量增大之后，执行时间变成了60s，而我设置的间隔时间是30s，这样就会出现上次任务还没执行完成，下次任务就开始执行了。所以，在这种情况下，我们要禁止quart的并发操作。
     *
     * 2种方式：
     *  spring中将job的concurrent属性设置为false。默认是true 如下：
     *   <bean id="scheduleJob" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
     *         <property name="targetObject" ref="scheduleBusinessObject"/>
     *         <property name="targetMethod" value="doIt"/>
     *         <property name="concurrent" value="false"/>
     *    </bean>
     *   job类上加上注解@DisallowConcurrentExecution。
     *      @DisallowConcurrentExecution
     *      public class HelloQuartz implements Job {
     *          @Override
     *          public void execute(JobExecutionContext jobExecutionContext) {
     *              JobDetail detail = jobExecutionContext.getJobDetail();
     *              String name = detail.getJobDataMap().getString("name");
     *              System.out.println("my job name is  " + name + " at " + new Date());
     *              }
     *          }
     *    注意：@DisallowConcurrentExecution是对JobDetail实例生效，如果一个job类被不同的jobdetail引用，这样是可以并发执行。
     *    Quartz是一个纯java开发的开源框架，对于java出身的程序员来讲，不管是api还是文档相对还是很友好的，而且使用起来也很方便。
     *    其实quartz最主要的几个接口就是 scheduler·、job、jobdetai、Trigger、jobBuilder。其中最主要的还是Trigger，
     *    再深一点，最主要的还是cron表达式。
     *
     */
