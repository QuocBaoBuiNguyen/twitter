package com.twitter.ms.config;

import com.gmail.merikbest2015.dto.request.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AmqpProducer {
    private String rabbitMqHost = "localhost:5672";
    @Value("${rabbitmq.internal-mail.exchanges}")
    private String exchange;
    @Value("${rabbitmq.internal-mail.routing-keys}")
    private String routingKey;

    private final AmqpTemplate amqpTemplate;

    public void sendEmail(EmailRequest emailRequest) {
        amqpTemplate.convertAndSend(exchange, routingKey, emailRequest);
    }
}
