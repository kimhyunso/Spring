package com.example.chapter11;

import com.example.chapter11.domain.Address;
import com.example.chapter11.domain.Member;
import com.example.chapter11.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chapter11Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter11Application.class, args);
	}

}
