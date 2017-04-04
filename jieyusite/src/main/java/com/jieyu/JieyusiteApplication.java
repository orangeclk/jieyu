package com.jieyu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class JieyusiteApplication {
	public static void main(String[] args) {
		SpringApplication.run(JieyusiteApplication.class, args);
	}

	@Bean
	SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}
}
