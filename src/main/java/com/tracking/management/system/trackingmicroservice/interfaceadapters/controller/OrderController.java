package com.tracking.management.system.trackingmicroservice.interfaceadapters.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tracking.management.system.trackingmicroservice.entities.Delivery;
import com.tracking.management.system.trackingmicroservice.frameworks.messaging.order.OrderProduceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderController {
    @Autowired
    private OrderProduceMessage orderProduceMessage;


    public void updateStatusOrder(Delivery delivery) throws JsonProcessingException {

        orderProduceMessage.sendMessageAlterStatusOrder(delivery.getId(), delivery.getStatus(), delivery.getTrakingCode(), Integer.valueOf(delivery.getOrderId()));
    }
}
