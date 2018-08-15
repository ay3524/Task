package sample.app.task.scheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import sample.app.task.network.DataUtils;

public class MyJobService extends JobService{
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        MyJobTask myJobTask = new MyJobTask();
        myJobTask.fetchDataFromServer();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
