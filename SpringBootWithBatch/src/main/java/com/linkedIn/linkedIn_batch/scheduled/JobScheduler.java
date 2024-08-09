package com.linkedIn.linkedIn_batch.scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
//@RequiredArgsConstructor
//public class JobScheduler {
//
//    private final JobLauncher jobLauncher;
//    private final JobRegistry jobRegistry;
//
//    @Scheduled(cron = "${batch.job.cron}")
//    public void runJob() {
//        try {
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addString("item", "shoes")
//                    .addDate("run.date", new Date())
//                    .addString("type", "roses")
//                    .toJobParameters();
//
//            Job job = jobRegistry.getJob("job");
//            jobLauncher.run(job, jobParameters);
//        } catch (Exception e) {
//
//        }
//    }
//}