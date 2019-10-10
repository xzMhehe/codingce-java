package com.young.quartz.job;

import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.logging.Logger;

public class RamJobTest {

    static final Logger logger = Logger.getLogger(RamJob.class.getName());

    @Test
    public void RamJobTest() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.shutdown();

    }


}
