package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.example.demo.run"})

public class DemoApplication {
	public static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		logger.info("something changed!");
	}
}
