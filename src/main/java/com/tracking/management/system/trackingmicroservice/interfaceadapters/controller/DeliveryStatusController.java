package com.tracking.management.system.trackingmicroservice.interfaceadapters.controller;

import com.tracking.management.system.trackingmicroservice.entities.Delivery;
import com.tracking.management.system.trackingmicroservice.interfaceadapters.gateways.TrackingGateway;
import com.tracking.management.system.trackingmicroservice.util.enums.TrackingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryStatusController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryStatusController.class);

    @Autowired
    private OrderController orderController;

    @Autowired
    private TrackingGateway trackingGateway;

    public void updateToCancel(Integer deliveryId, TrackingStatus status){
        Delivery delivery = trackingGateway.findById(deliveryId);


    }

}
