package org.db1.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootApplication
public class ApiApplication {

	private static final Logger log = LogManager.getLogger(ApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		log.info("Aplicação iniciou em {}", LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
	}

}
