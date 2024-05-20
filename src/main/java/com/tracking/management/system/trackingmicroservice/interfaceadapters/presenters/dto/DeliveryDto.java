package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto;

import com.tracking.management.system.trackingmicroservice.util.enums.TrackingStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class DeliveryDto {
    private String trakingCode;
    private TrackingStatus status;
    private double value;
    private LocalDate dateEnd;

}
