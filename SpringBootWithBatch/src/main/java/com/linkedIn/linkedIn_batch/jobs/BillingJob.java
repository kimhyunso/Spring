package com.linkedIn.linkedIn_batch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

public class BillingJob {
    @Bean
    public Step sendInvoiceStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("invoiceStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Invoice is sent to the customer");
                        return RepeatStatus.FINISHED;
                    }

                }, transactionManager)
                .build();
    }

    @Bean
    public Step nestedBillingJobStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

        return new StepBuilder("nestedBillingJobStep", jobRepository)
                .job(billingJob(jobRepository, transactionManager))
                .build();
    }


    @Bean
    public Job billingJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("billingJob", jobRepository)
                .start(sendInvoiceStep(jobRepository, transactionManager))
                .build();
    }


}
