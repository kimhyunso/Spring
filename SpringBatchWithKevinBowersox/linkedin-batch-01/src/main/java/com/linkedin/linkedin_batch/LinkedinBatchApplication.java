package com.linkedin.linkedin_batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
@EnableBatchProcessing
public class LinkedinBatchApplication {

	@Autowired
	private JobRegistry jobRegistry;
	@Autowired
	private JobLauncher jobLauncher;


	@Bean
	public Step packageItemStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("packageItemStep", jobRepository)
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						System.out.println("The item has been packaged.");
						return RepeatStatus.FINISHED;
					}
				}, transactionManager)
				.build();
	}

	@Bean
	public Job deliveryPackageJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {

		return new JobBuilder("deliveryPackageJob", jobRepository)
				.start(packageItemStep(jobRepository, transactionManager))
				.build();
	}



	public static void main(String[] args) {
		SpringApplication.run(LinkedinBatchApplication.class, args);
	}

}
