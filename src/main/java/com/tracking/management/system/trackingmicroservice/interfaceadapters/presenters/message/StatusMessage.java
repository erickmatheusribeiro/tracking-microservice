package com.tracking.management.system.trackingmicroservice.interfaceadapters.presenters.message;

import com.tracking.management.system.trackingmicroservice.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusMessage {

    private String orderId;

    private Status status;

    private String tracking;

    private String url;
}
