package com.linkedIn.linkedIn_batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor
@EnableBatchProcessing
public class LinkedinBatchApplication implements CommandLineRunner {

	private final JobLauncher jobLauncher;
	private final JobRegistry jobRegistry;

	public static void main(String[] args) {
		SpringApplication.run(LinkedinBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("item", "shoes")
				.addDate("run.date", new Date())
				.addString("type", "roses")
				.toJobParameters();

		Job job = jobRegistry.getJob("job");
		jobLauncher.run(job, jobParameters);
	}
}
