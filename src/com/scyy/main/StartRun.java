package com.scyy.main;


import com.scyy.task.GetTempJOB;
import com.scyy.task.JobManager;
import org.quartz.SchedulerException;

import java.text.ParseException;


/**
 * Created by LYH on 2016-03-25.
 */
public class StartRun {

    public static void main(String[] args) {

        GetTempJOB job = new GetTempJOB();
        JobManager jobManager = new JobManager();
        try {
            jobManager.addJob("lyh",job,3600);
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
