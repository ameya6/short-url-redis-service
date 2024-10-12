package com.url;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Log4j2
public class ShortURLRedisApplication {
	public static void main(String[] args) throws UnknownHostException {
		System.setProperty("hostName", InetAddress.getLocalHost().getHostName());
		System.setProperty("hostAddress", InetAddress.getLocalHost().getHostAddress());
		SpringApplication.run(ShortURLRedisApplication.class, args);
	}
}
