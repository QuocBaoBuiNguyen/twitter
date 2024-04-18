package com.twitter.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.twitter.chat", "com.gmail.merikbest2015"})
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
public class MsChatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsChatServiceApplication.class, args);
	}

}
