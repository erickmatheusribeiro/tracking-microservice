package com.tracking.management.system.trackingmicroservice.frameworks.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracking.management.system.trackingmicroservice.util.MessageUtil;
import com.tracking.management.system.trackingmicroservice.util.enums.TrackingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class ConsumeMessageFallback {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PublishMessageQueue publishMessageQueue;

    private static final int MAX_RETRY_BEFORE_CANCELED = 5;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeMessageFallback.class);

    @RabbitListener(queues = "q.fall-back-process")
    public void handleConsumeError(Message message) {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();

        Map<String, Object> death = (Map<String, Object>) ((List<Object>) headers.get("x-death")).get(0);

        String queue = death.get("queue").toString();

        int retry = 0;

        if (headers.containsKey("numberOfAttempts")) {
            retry = (int) headers.get("numberOfAttempts");
        }

        if (retry >= MAX_RETRY_BEFORE_CANCELED) {
            handleCancelationProcess(message);
        } else {
            message.getMessageProperties().getHeaders().put("numberOfAttempts", retry + 1);

            handleRetryProcess(message, queue);
        }
    }

    private void handleRetryProcess(Message message, String queue) {
        publishMessageQueue.sendMessage(message, queue);
    }

    private void handleCancelationProcess(Message message) {
        String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);

        try {
            JsonNode node = objectMapper.readTree(messageBody);

//            statusPublishMessage.sendMessage(node.get("orderId").asInt(), TrackingStatus.CANCELED);
        } catch (JsonProcessingException e) {
            LOGGER.error(MessageUtil.getMessage("LOG_DISCART_MESSAGE_FAILURE_CONVERSION", messageBody));
        }
    }
}