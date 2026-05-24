package com.mongoo.life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MongooLifeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongooLifeApiApplication.class, args);
	}
}
