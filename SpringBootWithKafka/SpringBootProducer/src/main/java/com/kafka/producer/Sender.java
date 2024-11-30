package com.kafka.producer;

import com.kafka.producer.config.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class Sender {
    private final KafkaTemplate<Integer, String> template;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        context.getBean(Sender.class).send("i love you", 42);
    }

    public void send(String toSend, int key) {
        template.send("topic1", key, toSend);
    }
}
