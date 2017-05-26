package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:/springintegration-config.xml")
public class SpringBootIntegrationWithMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntegrationWithMqApplication.class, args);
	}
}
