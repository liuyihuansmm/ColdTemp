package com.scyy.task;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

/**
 * Created by LYH on 2016-03-29.
 */
public class JobManager {

    private static SchedulerFactory sf = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "group1";
    private static String TRIGGER_GROUP_NAME = "trigger1";

    /**
     *  添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     * @param jobName 任务名
     * @param job     任务
     * @param time    时间设置
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void addJob(String jobName, Job job, int time)
            throws SchedulerException, ParseException {
        Scheduler sched = sf.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(jobName, JOB_GROUP_NAME).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName,TRIGGER_GROUP_NAME).startNow().withSchedule(
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(time).repeatForever()
        ).build();
        sched.scheduleJob(jobDetail, trigger);
        //启动
            sched.start();

    }
}
