package com.linkedIn.linkedIn_batch.config;

import com.linkedIn.linkedIn_batch.decider.DeliveryDecider;
import com.linkedIn.linkedIn_batch.decider.ReceiptDecider;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class deliveryJob {

    @Bean
    public JobExecutionDecider decider() {
        return new DeliveryDecider();
    }

    @Bean
    public JobExecutionDecider receiptDecider() { return new ReceiptDecider(); }

    @Bean
    public Step thinkCustomerStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("thinkCustomerStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Thinking the Customer \n");
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager).build();
    }

    @Bean
    public Step leaveAtDoorStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("leaveAtDoorStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("The customer leave at door \n");
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager).build();
    }


    @Bean
    public Step refundStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("refundStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Refunding customer money\n");
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager).build();
    }

    @Bean
    public Step storePackageStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("storePackageStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Storing the package while the customer address is located.\n");
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager).build();
    }


    @Bean
    public Step givePackageToCustomerStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("givePackageToCustomerStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Given the package to the customer\n");
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager).build();
    }

    @Bean
    public Step dirveToAddressStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        boolean GOT_LOST = false;

        return new StepBuilder("driveToAddressStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                        if (GOT_LOST) {
                            throw new RuntimeException("Got lost driving to the address");
                        }

                        System.out.println("Successfully arrived at the address\n");
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager).build();
    }

    @Bean
    public Step packageItemStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("packageItemStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        String item = chunkContext.getStepContext().getJobParameters().get("item").toString();
                        String date = chunkContext.getStepContext().getJobParameters().get("run.date").toString();

                        System.out.println(String.format("The %s has been packaged on %s.\n", item, date));
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager)
                .build();
    }

    @Bean
    public Job deliveryPackageJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {

        return new JobBuilder("deliveryPackageJob", jobRepository)
                .start(packageItemStep(jobRepository, transactionManager))
                .next(dirveToAddressStep(jobRepository, transactionManager)) // 실패했을 경우
                    .on("FAILED").fail()
                .from(dirveToAddressStep(jobRepository, transactionManager)) // 실패 이외의 경우
                    .on("*").to(decider())
                        .on("PRESENT").to(givePackageToCustomerStep(jobRepository, transactionManager))
                            .next(receiptDecider()).on("CORRECT").to(thinkCustomerStep(jobRepository, transactionManager))
                            .from(receiptDecider()).on("INCORRECT").to(refundStep(jobRepository, transactionManager))
                .from(decider())
                    .on("NOT_PRESENT").to(leaveAtDoorStep(jobRepository, transactionManager))
                .end()
                .build();
    }


}
