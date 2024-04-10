package com.open.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PortalApplication {
	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}
}