package com.aplazo.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.aplazo.customers.constants.Properties;

@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
public class AplazoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplazoApplication.class, args);
	}

}
