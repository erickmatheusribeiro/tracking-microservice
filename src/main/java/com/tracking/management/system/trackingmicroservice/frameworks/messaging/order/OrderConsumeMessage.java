package com.tracking.management.system.trackingmicroservice.frameworks.messaging.order;

import com.fasterxml.jackson.databind.JsonNode;
import com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.ordening.OrdeningWeb;
import com.tracking.management.system.trackingmicroservice.util.exception.ExternalInterfaceException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumeMessage {
    @Autowired
    private OrdeningWeb ordeningWeb;

    @RabbitListener(queues = "status.update")
    public void handleOrderMessages(JsonNode jsonNode) throws ExternalInterfaceException {
        Integer deliveryId = jsonNode.get("deliveryId").asInt();
        ordeningWeb.updateStatusOrder(deliveryId);
    }
}
