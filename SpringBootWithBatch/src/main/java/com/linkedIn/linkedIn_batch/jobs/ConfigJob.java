package com.linkedIn.linkedIn_batch.jobs;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Date;

@Configuration
// @Component
public class ConfigJob {

    @Bean
    public Job configTestJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("configTestJob", jobRepository)
                .start(configTestStep(jobRepository, transactionManager, null, null))
                .build();
    }

    @Bean
    @JobScope
    public Step configTestStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                               @Value("#{jobParameters[item]}") String item,
                               @Value("#{jobParameters[type]}") String type) {
        return new StepBuilder("configTestStep", jobRepository)
                .tasklet(configTestTasklet(item, type), transactionManager)
                .build();
    }

    public Tasklet configTestTasklet(String item, String type) {
        return (contribution, chunkContext) -> {
            System.out.println(item);
            System.out.println(type);
            return RepeatStatus.FINISHED;
        };
    }

}
