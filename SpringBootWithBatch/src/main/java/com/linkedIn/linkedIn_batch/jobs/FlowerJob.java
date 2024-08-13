package com.linkedIn.linkedIn_batch.jobs;

import com.linkedIn.linkedIn_batch.listener.FlowersSelectionStepExecutionListener;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class FlowerJob {

    private final SimpleFlow deliveryFlow;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public StepExecutionListener selectionFlowerListener() {
        return new FlowersSelectionStepExecutionListener();
    }

    @Bean
    public Job prepareFlowersJob() {
        Job build = new JobBuilder("prepareFlowersJob", jobRepository)
                .start(selectFlowersStep())
                .on("TRIM_REQUIRED").to(removeThornsStep()).next(arrangeFlowersStep())
                .from(selectFlowersStep())
                .on("NO_TRIM_REQUIRED").to(arrangeFlowersStep())
                .from(arrangeFlowersStep()).on("*").to(deliveryFlow)
                .end()
                .build();
        return build;
    }


    @Bean
    public Step arrangeFlowersStep() {
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
    public Step selectFlowersStep() {
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
    public Step removeThornsStep() {
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
