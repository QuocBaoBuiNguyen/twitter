package com.twitter.ms.feign;


import com.gmail.merikbest2015.configuration.FeignConfiguration;
import com.gmail.merikbest2015.dto.request.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.gmail.merikbest2015.constants.FeignConstants.NOTIFICATION_SERVICE;
import static com.gmail.merikbest2015.constants.PathConstants.API_V1_NOTIFICATION;

@FeignClient(value = NOTIFICATION_SERVICE, url = "${service.downstream-url.ms-notification-service}", path = API_V1_NOTIFICATION, configuration = FeignConfiguration.class)
public interface NotificationClient {

    @CircuitBreaker(name = NOTIFICATION_SERVICE)
    @PostMapping
    void sendNotification(@RequestBody NotificationRequest request);
}
