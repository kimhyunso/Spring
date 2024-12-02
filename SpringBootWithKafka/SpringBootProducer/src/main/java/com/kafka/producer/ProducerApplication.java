package com.kafka.producer;

import com.kafka.producer.domain.Foo;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RequiredArgsConstructor
@SpringBootApplication
public class ProducerApplication {

	private final KafkaTemplate<Integer, String> template;

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner() {
		return args -> {

			Foo foo = Foo.builder()
					.name("foo")
					.age(20)
					.build();

			// async
			ProducerRecord<Integer, String> record = new ProducerRecord<>("topic1", "iloveyou");

//			CompletableFuture<SendResult<String, Foo>> future = template.send(record);
//			future.whenComplete((result, ex) -> {
//				if (ex == null) {
//					System.out.println("결과:" + result);
//				}
//				System.out.println("에러:" + ex);
//			});
//
//			System.out.println("테스트1");

			// sync
			try {
				template.send(record).get(10, TimeUnit.SECONDS);
				System.out.println(record);
			} catch (ExecutionException e) {
				System.out.println("error");
			} catch (TimeoutException | InterruptedException e) {
				System.out.println("TimeoutError");
			}

			System.out.println("테스트1");
		};
	}
}
