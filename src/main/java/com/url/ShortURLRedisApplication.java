package com.url;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Log4j2
public class ShortURLRedisApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShortURLRedisApplication.class, args);
		log.info("Hey");
	}
}
