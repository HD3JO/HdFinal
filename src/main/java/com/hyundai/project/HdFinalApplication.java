package com.hyundai.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HdFinalApplication {	

	public static void main(String[] args) {
		SpringApplication.run(HdFinalApplication.class, args);
	}
}
