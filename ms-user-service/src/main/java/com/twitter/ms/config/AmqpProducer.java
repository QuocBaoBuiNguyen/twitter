//package com.twitter.ms.config;
//
//import com.gmail.merikbest2015.dto.request.EmailRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class AmqpProducer {
//    private String rabbitMqHost = "localhost:5672";
////    @Value("${rabbitmq.exchanges.internal}")
//    private String exchange = "internal.exchange";
////    @Value("${rabbitmq.routing-keys.internal-mail}")
//    private String routingKey = "internal.mail.routing-key";
//    private final AmqpTemplate amqpTemplate;
//
//    public void sendEmail(EmailRequest emailRequest) {
//        amqpTemplate.convertAndSend(exchange, routingKey, emailRequest);
//    }
//}
