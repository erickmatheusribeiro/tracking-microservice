package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.messages;

import com.tracking.management.system.trackingmicroservice.util.enums.TrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatusMessage {
    private Integer deliveryId;
    private TrackingStatus status;
    private String trakingCode;
    private Integer orderId;
}
