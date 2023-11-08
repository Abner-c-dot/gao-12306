package org.opengoofy.index12306.framework.starter.idempotent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdempotentApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdempotentApplication.class, args);
	}

}
