package com.tracking.management.system.trackingmicroservice.frameworks.external.messaging.logistics;

import com.tracking.management.system.trackingmicroservice.frameworks.external.interfaces.ordening.OrderingWeb;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.message.StatusMessage;
import com.tracking.management.system.trackingmicroservice.util.MessageUtil;
import com.tracking.management.system.trackingmicroservice.util.enums.Status;
import com.tracking.management.system.trackingmicroservice.util.exception.ExternalInterfaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticsMessageConsume {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogisticsMessageConsume.class);

    @Autowired
    private OrderingWeb orderingWeb;

    @RabbitListener(queues = "logistics.tracking.status")
    public void consumeMessage(StatusMessage statusMessage) throws ExternalInterfaceException {
        if (Status.CANCELADO.equals(statusMessage.getStatus())) {
            orderingWeb.updateToCacel(statusMessage);
        } else if (Status.PREPARACAO.equals(statusMessage.getStatus())) {
            orderingWeb.udpateToOnCarrier(statusMessage);
        } else if (Status.ENTREGUE.equals(statusMessage.getStatus())) {
            orderingWeb.udpateToDelivered(statusMessage);
        } else {
            LOGGER.error(MessageUtil.getMessage("LOG_DISCART_MESSAGE", statusMessage.getOrderId() + ":" + statusMessage.getStatus()));
        }
    }
}
