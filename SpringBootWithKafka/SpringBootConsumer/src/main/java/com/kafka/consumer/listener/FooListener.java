package com.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;

public class FooListener {

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(String in) {
        System.out.println(in);
    }

}