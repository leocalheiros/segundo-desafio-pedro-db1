package org.db1.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.db1.api.domain.util.LocalDateTimeUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	private static final Logger log = LogManager.getLogger(ApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		log.info("Aplicação iniciou em {}", LocalDateTimeUtils.setSaoPauloDateTime());
	}

}
