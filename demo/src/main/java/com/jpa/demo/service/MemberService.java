package com.jpa.demo.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final EntityManager em;

    @Autowired
    public MemberService(EntityManager em) {
        this.em = em;
    }


}
