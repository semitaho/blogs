package com.tahoo.guides.spring_ai_poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringAiPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiPocApplication.class, args);
	}

}
