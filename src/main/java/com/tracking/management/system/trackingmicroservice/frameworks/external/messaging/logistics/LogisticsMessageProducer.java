package com.tracking.management.system.trackingmicroservice.frameworks.external.messaging.logistics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.message.StatusMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class LogisticsMessageProducer {

    @Value("${messaging.queue.tracking.status}")
    private String queue;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(StatusMessage statusMessage) throws JsonProcessingException {
        Message message = new Message(objectMapper.writeValueAsString(statusMessage).getBytes(StandardCharsets.UTF_8));
        message.getMessageProperties().setContentType(StandardCharsets.UTF_8.name());
        message.getMessageProperties().setContentType("application/json");

        template.send(queue, message);
    }
}
