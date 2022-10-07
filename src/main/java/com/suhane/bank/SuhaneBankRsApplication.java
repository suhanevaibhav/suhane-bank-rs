package com.suhane.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SuhaneBankRsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuhaneBankRsApplication.class, args);
	}

}
