package com.example.demo.config;

import com.example.demo.domain.Person;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;

public class BatchConfiguration {

    @Bean
    public FlatFileItemReaderBuilder<Person> reader(){
        return new FlatFileItemReaderBuilder<Person>();
    }

}
