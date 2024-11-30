package com.kafka.producer.config;

import com.kafka.producer.ProducerApplication;
import com.kafka.producer.Sender;
import com.kafka.producer.domain.Foo;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.ObjectSerializationCache;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.ToStringSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class Config {

    @Bean
    public ProducerFactory<String, Foo> producerFactory() {
        return new DefaultKafkaProducerFactory<>(senderProps());
    }

    private Map<String, Object> senderProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ToStringSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, Foo> kafkaTemplate(ProducerFactory<String, Foo> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
