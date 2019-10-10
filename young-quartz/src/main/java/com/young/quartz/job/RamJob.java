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
