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

@SpringBootApplication
public class ProducerApplication {

	@Autowired
	private KafkaTemplate<String, Foo> template;

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
			ProducerRecord<String, Foo> record = new ProducerRecord<>("topic1", foo);

			CompletableFuture<SendResult<String, Foo>> future = template.send(record);
			future.whenComplete((result, ex) -> {
				if (ex == null) {
					System.out.println("결과:" + result);
				}
				System.out.println("에러:" + ex);
			});

			// sync
		};
	}
}
