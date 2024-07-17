package com.linkedIn.linkedIn_batch.config;

import com.linkedIn.linkedIn_batch.listener.FlowersSelectionStepExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

public class FlowerJob {

    @Bean
    public StepExecutionListener selectionFlowerListener() {
        return new FlowersSelectionStepExecutionListener();
    }

    @Bean
    public Job prepareFlowersJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("prepareFlowersJob", jobRepository)
                .start(selectFlowersStep(jobRepository, transactionManager))
                    .on("TRIM_REQUIRED").to(removeThornsStep(jobRepository, transactionManager)).next(arrangeFlowersStep(jobRepository, transactionManager))
                .from(selectFlowersStep(jobRepository, transactionManager))
                    .on("NO_TRIM_REQUIRED").to(arrangeFlowersStep(jobRepository, transactionManager))
                .end()
                .build();
    }


    @Bean
    public Step arrangeFlowersStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("arrangeFlowersStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Arrange Flowers for order");
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager)
                .build();
    }

    @Bean
    public Step selectFlowersStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("selectFlowersStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Gathering flowers for orders");
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager)
                .listener(selectionFlowerListener())
                .build();
    }

    @Bean
    public Step removeThornsStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("removeThornsStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Remove thorns from roses.");
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager)
                .build();
    }
}
