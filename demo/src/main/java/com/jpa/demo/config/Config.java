package com.jpa.demo.config;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private EntityManager em;

    @Autowired
    public Config(EntityManager em) {
        this.em = em;
    }


}
