package com.tracking.management.system.trackingmicroservice.frameworks.messaging.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tracking.management.system.trackingmicroservice.frameworks.messaging.PublishMessageQueue;
import com.tracking.management.system.trackingmicroservice.util.enums.TrackingStatus;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class OrderProduceMessage extends PublishMessageQueue {

    @Value("messaging.queue.status")
    private String orderQueue;

    public void sendMessageAlterStatusOrder(Integer deliveryId, TrackingStatus status, String trakingCode, Integer orderId ) throws JsonProcessingException {
        Message message = createMessage(deliveryId, status, trakingCode, orderId);

        super.sendMessage(message, orderQueue);
    }

    private Message createMessage(Integer deliveryId, TrackingStatus status, String trakingCode, Integer orderId) throws JsonProcessingException {
        ObjectNode json = super.objectMapper.createObjectNode();
        json.put("deliveryId", deliveryId);
        json.put("status", status.name());
        json.put("trakingCode", trakingCode);
        json.put("orderId", orderId);

        return new Message(super.objectMapper.writeValueAsString(json).getBytes(StandardCharsets.UTF_8));
    }

}
