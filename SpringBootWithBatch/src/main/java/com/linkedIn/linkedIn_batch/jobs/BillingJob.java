package com.linkedIn.linkedIn_batch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BillingJob {



    @Bean
    public SimpleFlow billingFlow(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new FlowBuilder<SimpleFlow>("billingFlow")
                .start(sendInvoiceStep(jobRepository, transactionManager))
                .build();
    }

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
    public Step nestedBillingStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("nestedBillingJobStep", jobRepository)
                .job(billing(jobRepository, transactionManager))
                .build();
    }


    @Bean
    public Job billing(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("billingJob", jobRepository)
                .start(sendInvoiceStep(jobRepository, transactionManager))
                .build();
    }



}
