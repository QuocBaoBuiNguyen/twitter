package com.twitter.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static com.gmail.merikbest2015.constants.FeignConstants.USER_SERVICE;
import static com.gmail.merikbest2015.constants.PathConstants.API_V1_AUTH;

@Configuration
public class ClientConfig {
        @Bean
        public WebClient webClient() {
            return WebClient.builder()
                    .baseUrl(String.format("http://%s:8081%s", USER_SERVICE, API_V1_AUTH))
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
        }
}
