package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.dto;

import com.tracking.management.system.trackingmicroservice.util.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class DeliveryDto {
    private String trakingCode;
    private Status status;
    private LocalDate dateEnd;

}
