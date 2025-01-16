package com.example.demo;

import com.example.demo.run.Location;
import com.example.demo.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class DemoApplication {
	public static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		logger.info("something changed!");
	}
	@Bean
	CommandLineRunner runner(){
		return args -> {
			Run run = new Run(1,"first run", LocalDateTime.now(),LocalDateTime.now().plus(1, ChronoUnit.HOURS),12, Location.INDOOR);
			logger.info("Run: "+run);


		};
	}


}
