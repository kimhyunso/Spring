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
	private KafkaTemplate<String, Object> template;

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner() {
		return args -> {
			Foo foo = Foo.builder()
					.age(10)
					.name("테스트")
					.build();

			ProducerRecord<String, Object> record = new ProducerRecord<>("topic1", foo);
			System.out.println(record);

//			CompletableFuture<SendResult<String, Object>> future = template.send(record);
//
//			future.whenComplete((result, ex) -> {
//				if (ex == null) {
//					System.out.println(result);
//				} else {
//					System.out.println(ex);
//				}
//			});
		};
	}
}
